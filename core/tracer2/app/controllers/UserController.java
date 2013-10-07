package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.MileStone;
import models.Project;
import models.Session;
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

public class UserController extends Controller {
	static UserController userController = null;
	static {
		userController = new UserController();
	}

	public static Result login() {
		ObjectNode response = Json.newObject();
		JsonNode json = request().body().asJson();
		String userName = "manzarul07@gmail.com";
		String password = "12qwaszx";
		Session session = null;
		try {
			
			  userName = json.get(JsonKey.USER_NAME).asText(); 
			  password =json.get(JsonKey.PASSWORD).asText();
			 
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
			for (int i = 0; i < userProjects.size(); i++) {
				projectList.add(userProjects.get(i).getProject());
			}
			data.setProjrctList(projectList);
			user.setPassword("");
			data.setUserInfo(session);
			return ok(TracerUtil.successResponse(data));
		} else {
			response.put(JsonKey.STATUS_CODE,
					ResponseCode.UnAuthorised.getErrorCode());
			response.put(JsonKey.STATUS_MESSAGE,
					ResponseCode.FAILURE.getErrorMessage());
			response.put(JsonKey.ERROR_MESSAGE,
					ResponseCode.UnAuthorised.getErrorMessage());
			return unauthorized(response);
		}
	}

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
		Project project = new Project();
		project.setCreated(new Date());
		project.setDescription(description);
		project.setName(projectName);
		project.setUpdated(new Date());
		Visibility visib =  Ebean.createQuery(Visibility.class).where().eq("id", visibility).findUnique();
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
		return ok(TracerUtil.successResponse(null));
	}

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
		} catch(Exception e) {
			TracerLogger.error(""+e, userController);
			return ok (TracerUtil.InvalidDataResponse());
		}
		Session userSession = Ebean.createQuery(Session.class).where()
				.eq("sessionId", sessionId).eq("user_id", userId).findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}

		Project	project = Ebean.createQuery(Project.class).where()
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
		String email = "manzarul07@gmail.com";
		String session = "";
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
		String name = "manzarul";
		String email = "manzarul07@gmail.com";
		String password = "12qwaszx";
		String phone = "9663890445";
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
		User user = Ebean.createQuery(User.class).where().eq("email", email)
				.findUnique();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		Ebean.update(user);
		return ok(TracerUtil.successResponse(null));
	}
}
