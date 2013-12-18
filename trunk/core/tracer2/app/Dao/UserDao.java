/**
 * 
 */
package Dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import responseBean.LoginResponseData;
import responseBean.MasterDataBean;

import util.Constants;
import util.DataMasking;
import util.PropertyReader;
import util.SendMail;
import util.TracerUtil;
import util.TrackLogger;
import util.Constants.TicketStatus;

import com.avaje.ebean.Ebean;

import models.Complexity;
import models.MileStone;
import models.Phase;
import models.Project;
import models.Session;
import models.Severity;
import models.Type;
import models.User;
import models.UserProject;
import models.Visibility;

/**
 * @author Manzarul.Haque
 * 
 */
public final class UserDao implements Cloneable {
	/*
	 * className name of class
	 */
	private static final String className = UserDao.class.getName();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static UserDao instance;

	static {
		instance = new UserDao();
	}

	/**
	 * UserDao Default constructor.
	 */
	private UserDao() {

	}

	/**
	 * no one can make clone
	 */
	public UserDao clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * this method is used to create single instance.
	 * 
	 * @return
	 */
	public synchronized static UserDao getInstance() {
		if (instance != null) {
			return instance;
		}
		instance = new UserDao();
		return instance;
	}

	/**
	 * this method will check user is valid or not. if user is valid then it
	 * will provide all project of that user.
	 * 
	 * @param userName
	 *            String user name
	 * @param password
	 *            String password
	 * @return LoginResponseData
	 */
	public LoginResponseData login(String userName, String password) {
		Session session = null;
		LoginResponseData data = null;
		User user = Ebean.createQuery(User.class).where().eq("email", userName).eq("password", password).findUnique();
		if (user != null) {
			session = Ebean.createQuery(Session.class).where().eq("user_id", user.getId()).findUnique();
			if (session != null) {
				Ebean.delete(session);
			}
			session = new Session();
			session.setSessionId(TracerUtil.getUniqueId(user.getEmail()));
			session.setUser(user);
			session.setUpdated(new Date());
			session.setCreated(new Date());
			Ebean.save(session);
			List<UserProject> userProjects = Ebean.createQuery(UserProject.class).where().eq("user_id", user.getId())
			        .findList();
			data = new LoginResponseData();
			List<Project> projectList = new ArrayList<Project>();
			if (userProjects != null) {
				for (int i = 0; i < userProjects.size(); i++) {
					projectList.add(userProjects.get(i).getProject());
				}
			}
			data.setProjectList(projectList);
			user.setPassword("");
			data.setUserInfo(session);
		}
		return data;
	}

	/**
	 * this method will save user project.
	 * 
	 * @param projectName
	 *            name of the project
	 * @param description
	 *            project description
	 * @param visibility
	 *            int (1-public,2-private,3-org level)
	 * @param userId
	 *            long user id
	 * @return true/false
	 */
	public boolean addProject(String projectName, String description, int visibility, long userId) {
		boolean response = true;
		try {
			Project project = new Project();
			project.setCreated(new Date());
			project.setDescription(description);
			project.setName(projectName);
			project.setUpdated(new Date());
			Visibility visib = Ebean.createQuery(Visibility.class).where().eq("id", visibility).findUnique();
			Ebean.save(visib);
			project.setVisibility(visib);
			User user = Ebean.createQuery(User.class).where().eq("id", userId).findUnique();
			project.setCreatedBy(user);
			Ebean.save(project);
			MileStone mileStone = new MileStone();
			mileStone.setName(Constants.DEFAULT_MILE_STONE);
			mileStone.setCreated(new Date());
			mileStone.setEnded(new Date((new Date().getTime() + 1000 * 60 * 60 * 24 * 7)));
			mileStone.setStatus(Constants.mileStoneSatus.active.toString());
			mileStone.setProject(project);
			Ebean.save(mileStone);
			UserProject userProject = new UserProject();
			userProject.setProject(project);
			userProject.setUser(user);
			Ebean.save(userProject);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		}
		return response;
	}

	/**
	 * this method is used to assign new user to a project.
	 * 
	 * @param userId
	 * @param projectId
	 * @return
	 */
	public boolean addUserToProject(long userId, long projectId) {
		boolean response = true;
		try {
			Project project = Ebean.createQuery(Project.class).where().eq("id", projectId).findUnique();
			User user = Ebean.createQuery(User.class).where().eq("id", userId).findUnique();
			UserProject userProject = new UserProject();
			userProject.setProject(project);
			userProject.setUser(user);
			Ebean.save(userProject);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		}
		return response;
	}

	/**
	 * this method is for inviting user to join tracer.
	 * 
	 * @param email
	 *            user email
	 * @return true/false
	 */
	public boolean inviteUser(final String email) {
		boolean response = true;
		try {
		//	User user = new User();
		//	user.setEmail(email);
		//	Ebean.save(user);
			final String mailSubject = PropertyReader.readProperty("tracer.invitation.text") + "</br>"
			        + Constants.SERVER_URL+"?"+DataMasking.encrypt(email, Constants.SALT);
			final String subject = PropertyReader.readProperty("tracer.registration");
			new Thread(new Runnable() {
				@Override
				public void run() {
					SendMail.getMailInstance().sendMail(email, mailSubject, subject);
				}
			}).start();

		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		}
		return response;
	}

	/**
	 * this method will crate new mile stone.
	 * 
	 * @param name
	 *            mile stone name
	 * @param status
	 *            mile stone status
	 * @param endDate
	 *            mile stone end date
	 * @param project
	 *            project details for mile stone
	 * @return boolean
	 */
	public boolean createMileStone(String name, String status, String endDate, Project project) {
		boolean response = true;
		try {
			MileStone mileStone = new MileStone();
			Date endedDate = null;
			try {
				endedDate = dateFormat.parse(endDate);
			} catch (Exception e) {
				TrackLogger.error(e.getMessage(), className);
			}
			mileStone.setCreated(new Date());
			mileStone.setEnded(endedDate);
			mileStone.setName(name);
			mileStone.setProject(project);
			mileStone.setStatus(status);
			Ebean.save(mileStone);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		}
		return response;
	}
	
	/**
	 * this method will provide master data means 
	 * current mile stone, all Severity Label,Complexity
	 * type ,State etc.
	 * @param projectId
	 * @return
	 */
	public MasterDataBean getMasterData(long projectId) {
		MasterDataBean masterDataBean = null;
		try {
			//here we will check if projectId is zero then send all mile stone 
			//other wise send only those mile stone which is related to that project only.
			List<MileStone> mileStone = null;
			if(projectId==0) {
			 mileStone = Ebean.createQuery(MileStone.class).findList();
			} else {
				mileStone = Ebean.createQuery(MileStone.class).where().eq("project_id", projectId).findList();
			}
			List<Complexity> complexities = Ebean.createQuery(Complexity.class).findList();
			List<Severity> severities = Ebean.createQuery(Severity.class).findList();
			List<Phase> phase = Ebean.createQuery(Phase.class).findList();
			List<Type> types = Ebean.createQuery(Type.class).findList();
			List<String> status = new ArrayList<String>();
			TicketStatus ticketStatus[] = TicketStatus.values();
			for (int i = 0; i < ticketStatus.length; i++) {
				status.add(ticketStatus[i].name());
			}
			masterDataBean = new MasterDataBean();
			masterDataBean.setComplexities(complexities);
			masterDataBean.setMileStone(mileStone);
			masterDataBean.setPhases(phase);
			masterDataBean.setSeverity(severities);
			masterDataBean.setTypes(types);
			masterDataBean.setTicketStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			TrackLogger.error(e.getMessage(), className);
		}
		return masterDataBean;
	}
	
	/**
	 * this method will provide list of users.
	 * @param userId
	 * @return
	 */
	public List<User> getallUser(long userId) {
		List<User> users = null;
		try {
			users = Ebean.createQuery(User.class).where().ne("id", userId).findList();
		} catch (Exception e) {
			e.printStackTrace();
			TrackLogger.error(e.getMessage(), className);
		}
		return users;
	}
	
	/**
	 * this method will check any mile stone with this project 
	 * is active or not.
	 * @param projectId
	 * @return
	 */
	public boolean isMileStoneActive(long projectId) {
		boolean response = false;
		List<MileStone> mileStones = Ebean.createQuery(MileStone.class).where().eq("project_id", projectId).findList();
		for (int i = 0; i < mileStones.size(); i++) {
			if (Constants.mileStoneSatus.active.toString().equalsIgnoreCase(mileStones.get(i).getStatus())) {
				response = true;
				break;
			}
		}
		return response;
	}
}
