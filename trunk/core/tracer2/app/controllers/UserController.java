package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import responseBean.LoginResponseData;
import util.JsonKey;
import util.PropertyReader;
import util.ResponseCode;
import util.SendMail;
import util.TracerLogger;
import util.TracerUtil;

import com.avaje.ebean.Ebean;

/**
 * this controller will handle all user related operations.
 * 
 * @author Manzarul.Haque
 * 
 */
public class UserController extends Controller {
	static UserController userController = null;
	static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static {
		userController = new UserController();
	}

	/**
	 * this method is used to user login.
	 * 
	 * @return
	 */
	public static Result login() {
		ObjectNode response = Json.newObject();
		JsonNode json = request().body().asJson();
		String userName = null;
		String password = null;
		Session session = null;
		try {
			userName = json.get(JsonKey.USER_NAME).asText();
			password = json.get(JsonKey.PASSWORD).asText();
		} catch (Exception e) {
			TracerLogger.error("" + e, userController);
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
		response.put(JsonKey.STATUS_CODE,
				ResponseCode.UnAuthorised.getErrorCode());
		response.put(JsonKey.STATUS_MESSAGE,
				ResponseCode.FAILURE.getErrorMessage());
		response.put(JsonKey.ERROR_MESSAGE,
				ResponseCode.UnAuthorised.getErrorMessage());
		return unauthorized(response);
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
			TracerLogger.error("" + e, userController);
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
		return ok(TracerUtil.successResponse(null));
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
			TracerLogger.error("" + e, userController);
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
		return ok(TracerUtil.successResponse(null));
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
			if (email == null || "".equals(email)) {
				throw new Exception();
			}
		} catch (Exception e) {
			TracerLogger.error("" + e, userController);
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
		return ok(TracerUtil.successResponse(null));
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
			TracerLogger.error("" + e, userController);
			return ok(TracerUtil.InvalidDataResponse());
		}
		User user = new User(); /*
								 * Ebean.createQuery(User.class).where().eq("email"
								 * , email) .findUnique();
								 */
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		Ebean.save(user);
		return ok(TracerUtil.successResponse(null));
	}

	/**
	 * this method will create new mile stone.
	 * 
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
			TracerLogger.error("" + e, userController);
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
			TracerLogger.error("" + e, userController);
		}
		mileStone.setCreated(new Date());
		mileStone.setEnded(endedDate);
		mileStone.setName(name);
		mileStone.setProject(project);
		mileStone.setStatus(status);
		Ebean.save(mileStone);
		return ok(TracerUtil.successResponse(null));
	}

	/**
	 * this method will create new tickets.
	 * 
	 * @return
	 */
	public static Result createTickets() {
		String title = null;
		String description = null;
		int phaseId;
		long createrId;
		long ownerId;
		long mileStoneId;
		int severityId;
		int complexityId;
		String sessionId = null;
		int projectId;
		String status;
		double estimatedHours;
		double actualHours;
		JsonNode json = request().body().asJson();
		try {
			title = json.get(JsonKey.TITLE).asText();
			description = json.get(JsonKey.DESCRIPTION).asText();
			sessionId = json.get(JsonKey.SESSION).asText();
			projectId = json.get(JsonKey.PROJECT_ID).asInt();
			createrId = json.get(JsonKey.USER_ID).asLong();
			status = json.get(JsonKey.STATUS).asText();
			phaseId = json.get(JsonKey.PHASE_ID).asInt();
			ownerId = json.get(JsonKey.OWNER_ID).asLong();
			mileStoneId = json.get(JsonKey.MILE_STONE_ID).asLong();
			severityId = json.get(JsonKey.SEVERITY).asInt();
			complexityId = json.get(JsonKey.COMPLEXITY_ID).asInt();
			estimatedHours = json.get(JsonKey.ESTIMATED_HOURS).asDouble();
			actualHours = json.get(JsonKey.ACTUAL_HOURS).asDouble();
		} catch (Exception e) {
			TracerLogger.error("" + e, userController);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = Ebean.createQuery(Session.class).where()
				.eq("sessionId", sessionId).eq("user_id", createrId)
				.findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		User user = userSession.getUser();
		Project project = Ebean.createQuery(Project.class).where()
				.eq("id", projectId).findUnique();
		MileStone mileStone = Ebean.createQuery(MileStone.class).where()
				.eq("id", mileStoneId).findUnique();
		User owner = Ebean.createQuery(User.class).where().eq("id", ownerId)
				.findUnique();
		Severity severity = Ebean.createQuery(Severity.class).where()
				.eq("id", severityId).findUnique();
		Complexity complexity = Ebean.createQuery(Complexity.class).where()
				.eq("id", complexityId).findUnique();
		Phase phase = Ebean.createQuery(Phase.class).where().eq("id", phaseId)
				.findUnique();
		if (project == null || user == null || owner == null) {
			return ok(TracerUtil.InvalidDataResponse());
		}
		Ticket ticket = new Ticket();
		ticket.setActulHours(actualHours);
		ticket.setEstimatedHours(estimatedHours);
		ticket.setComplexity(complexity);
		ticket.setCreated(new Date());
		ticket.setCreaterId(user);
		ticket.setDescription(description);
		ticket.setMileStone(mileStone);
		ticket.setOwnerId(owner);
		ticket.setPhase(phase);
		;
		ticket.setProject(project);
		ticket.setSeverity(severity);
		ticket.setTicketStatus(status);
		ticket.setTitle(title);
		ticket.setUpdated(new Date());
		Ebean.save(ticket);
		return ok(TracerUtil.successResponse(null));
	}

}
