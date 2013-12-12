package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Comment;
import models.Complexity;
import models.MileStone;
import models.Phase;
import models.Project;
import models.Session;
import models.Severity;
import models.Ticket;
import models.User;
import models.UserProject;
import models.Visibility;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import responseBean.LoginResponseData;
import util.JsonKey;
import util.PropertyReader;
import util.SendMail;
import util.TracerUtil;
import util.TrackLogger;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FetchConfig;

/**
 * this controller will handle all user related operations.
 * 
 * @author Manzarul.Haque
 * 
 */
public class UserController extends Controller {
	private static final  String className = UserController.class.getName();
	static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * this method is used to user login.
	 * 
	 * @return
	 */
	public static Result login() {
		JsonNode json = request().body().asJson();
		String userName =null;
		String password =null;
		Session session = null;
		try {
			userName = json.get(JsonKey.USER_NAME).asText();
			password = json.get(JsonKey.PASSWORD).asText();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		
		User user = Ebean.createQuery(User.class).where().eq("email", userName)
				.eq("password", password).findUnique();

		if (user != null) {
			session = Ebean.createQuery(Session.class).where()
					.eq("user_id", user.getId()).findUnique();
			if (session != null) {
				Ebean.delete(session);
			}
			session = new Session();
			session.setSessionId(TracerUtil.getUniqueId(user.getEmail()));
			session.setUser(user);
			session.setUpdated(new Date());
			session.setCreated(new Date());
			Ebean.save(session);
			List<UserProject> userProjects = Ebean
					.createQuery(UserProject.class).where()
					.eq("user_id", user.getId()).findList();
			LoginResponseData data = new LoginResponseData();
			List<Project> projectList = new ArrayList<Project>();
			if (userProjects != null) {
				for (int i = 0; i < userProjects.size(); i++) {
					projectList.add(userProjects.get(i).getProject());
				}
			}
			data.setProjectList(projectList);
			user.setPassword("");
			data.setUserInfo(session);
			return ok(TracerUtil.successResponse(data));
		}
		return ok(TracerUtil.unAuthorisedResponse());
	}

	/**
	 * this method is used to add a new project.
	 * 
	 * @return
	 */
	public static Result addProject() {
		JsonNode json = request().body().asJson();
		String projectName = "";
		String description = "";
		int visibility = 0;
		long userId = 0l;
		String sessionId = "";
		try {
			projectName = json.get(JsonKey.NAME).asText();
			description = json.get(JsonKey.DESCRIPTION).asText();
			visibility = json.get(JsonKey.VISIBLITY).asInt();
			userId = json.get(JsonKey.USER_ID).asLong();
			sessionId = json.get(JsonKey.SESSION).asText();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = Ebean.createQuery(Session.class).where()
				.eq("sessionId", sessionId).eq("user_id", userId).findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		boolean hasUserAccess = TracerUtil
				.checkUserAccess(userId, "inviteUser");
		if (!hasUserAccess) {
			return ok(TracerUtil.InvalidAccessResponse());
		}

		Project project = new Project();
		project.setCreated(new Date());
		project.setDescription(description);
		project.setName(projectName);
		project.setUpdated(new Date());
		Visibility visib = Ebean.createQuery(Visibility.class).where()
				.eq("id", visibility).findUnique();
		Ebean.save(visib);
		project.setVisibility(visib);
		User user = Ebean.createQuery(User.class).where().eq("id", userId)
				.findUnique();
		project.setCreatedBy(user);
		Ebean.save(project);
		MileStone mileStone = new MileStone();
		mileStone.setName("backlog");
		mileStone.setCreated(new Date());
		mileStone.setEnded(new Date((new Date().getTime() + 1000 * 60 * 60 * 24
				* 7)));
		mileStone.setStatus("active");
		mileStone.setProject(project);
		Ebean.save(mileStone);
		UserProject userProject = new UserProject();
		userProject.setProject(project);
		userProject.setUser(user);
		Ebean.save(userProject);
		return ok(TracerUtil.successResponse());
	}

	/**
	 * this method is used to add user in a project.
	 * 
	 * @return
	 */
	public static Result addUserToProject() {
		JsonNode json = request().body().asJson();
		String sessionId = "";
		long userId = 0l;
		long projectId = 0l;
		long assignUserId = 0l;
		try {
			sessionId = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			projectId = json.get(JsonKey.PROJECT_ID).asLong();
			assignUserId = json.get(JsonKey.ASSIGN_USER_ID).asLong();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = Ebean.createQuery(Session.class).where()
				.eq("sessionId", sessionId).eq("user_id", userId).findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}

		boolean hasUserAccess = TracerUtil
				.checkUserAccess(userId, "inviteUser");
		if (!hasUserAccess) {
			return ok(TracerUtil.InvalidAccessResponse());
		}
		Project project = Ebean.createQuery(Project.class).where()
				.eq("id", projectId).findUnique();
		User user = Ebean.createQuery(User.class).where()
				.eq("id", assignUserId).findUnique();
		UserProject userProject = new UserProject();
		userProject.setProject(project);
		userProject.setUser(user);
		Ebean.save(userProject);
		return ok(TracerUtil.successResponse());
	}

	/**
	 * this method is called when admin invite user , that user will get link to
	 * complete registration.
	 * 
	 * @return
	 */
	public static Result inviteUser() {
		String email = null;
		String session = null;
		long userId = 0L;
		JsonNode json = request().body().asJson();
		try {
			email = json.get(JsonKey.USER_NAME).asText();
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			if (TracerUtil.checkNullOrEmpty(email)) {
				throw new Exception();
			}
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = Ebean.createQuery(Session.class).where()
				.eq("sessionId", session).eq("user_id", userId).findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		boolean hasUserAccess = TracerUtil
				.checkUserAccess(userId, "inviteUser");
		if (!hasUserAccess) {
			return ok(TracerUtil.InvalidAccessResponse());
		}
		User user = new User();
		user.setEmail(email);
		Ebean.save(user);
		String mailSubject = PropertyReader
				.readProperty("tracer.invitation.text");
		mailSubject = mailSubject + "</br>"
				+ "http://idc.tarento.com:9000/register";
		String subject = PropertyReader.readProperty("tracer.registration");
		SendMail.getMailInstance().sendMail(email, mailSubject, subject);
		return ok(TracerUtil.successResponse());
	}

	/**
	 * this method is used to register new user .
	 * 
	 * @return
	 */
	public static Result registration() {
		String name = null;
		String email = null;
		String password = null;
		String phone = null;
		JsonNode json = request().body().asJson();
		try {
			name = json.get(JsonKey.NAME).asText();
			email = json.get(JsonKey.USER_NAME).asText();
			password = json.get(JsonKey.PASSWORD).asText();
			phone = json.get(JsonKey.PHONE_NUMBER).asText();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		User user = new User(); 
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		Ebean.save(user);
		return ok(TracerUtil.successResponse());
	}

	/**
	 * this method will create new mile stone.
	 * @return
	 */
	public static Result createMileStone() {
		String name = null;
		String sessionId = null;
		String ended = null;
		int projectId;
		String status;
		long userId;
		JsonNode json = request().body().asJson();
		try {
			name = json.get(JsonKey.NAME).asText();
			sessionId = json.get(JsonKey.SESSION).asText();
			ended = json.get(JsonKey.ENDED_DATE).asText();
			projectId = json.get(JsonKey.PROJECT_ID).asInt();
			userId = json.get(JsonKey.USER_ID).asLong();
			status = json.get(JsonKey.STATUS).asText();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = Ebean.createQuery(Session.class).where()
				.eq("sessionId", sessionId).eq("user_id", userId).findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}

		Project project = Ebean.createQuery(Project.class).where()
				.eq("id", projectId).findUnique();
		if (project == null) {
			return ok(TracerUtil.InvalidDataResponse());
		}
		MileStone mileStone = new MileStone();
		Date endedDate = null;
		try {
			endedDate = dateFormat.parse(ended);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
		}
		mileStone.setCreated(new Date());
		mileStone.setEnded(endedDate);
		mileStone.setName(name);
		mileStone.setProject(project);
		mileStone.setStatus(status);
		Ebean.save(mileStone);
		return ok(TracerUtil.successResponse());
	}

}
