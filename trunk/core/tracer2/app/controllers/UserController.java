package controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import models.Project;
import models.Session;
import models.User;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import responseBean.LoginResponseData;
import responseBean.MasterDataBean;
import services.service.UserService;
import services.serviceFactory.serviceFactory;
import services.serviceImpl.UserServiceImpl;
import util.Constants;
import util.JsonKey;
import util.TracerUtil;
import util.TrackLogger;

import com.avaje.ebean.Ebean;

import dataAccess.dao.UserDao;
import dataAccess.daoFactory.DaoFactory;
import dataAccess.daoImple.UserDaoImpl;

/**
 * this controller will handle all user related operations.
 * 
 * @author Manzarul.Haque
 * 
 */
public class UserController extends Controller {
	private static final String className = UserController.class.getName();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * this method is used to user login.
	 * 
	 * @return Result
	 */
	public static Result login() {
		JsonNode json = request().body().asJson();
		String userName = null;
		String password = null;
		try {
			userName = json.get(JsonKey.USER_NAME).asText();
			password = json.get(JsonKey.PASSWORD).asText();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		try {
			UserService userService = (UserServiceImpl) serviceFactory.getInstance(Constants.USER_SERVICE);
			LoginResponseData data = userService.login(userName, password);
			if (data != null) {
				return ok(TracerUtil.successResponse(data));
			}
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.failureResponse());
		}
		return ok(TracerUtil.unAuthorisedResponse());
	}

	/**
	 * this method is used to add a new project.
	 * 
	 * @return Result
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

		Session userSession = TracerUtil.checkSession(sessionId, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		boolean hasUserAccess = TracerUtil.checkUserAccess(userId, "inviteUser");
		if (!hasUserAccess) {
			return ok(TracerUtil.InvalidAccessResponse());
		}
		UserService userService = (UserServiceImpl) serviceFactory.getInstance(Constants.USER_SERVICE);
		boolean response = userService.addProject(projectName, description, visibility, userId);
		if (response) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}

	/**
	 * this method is used to add user in a project.
	 * 
	 * @return Result
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
		Session userSession = TracerUtil.checkSession(sessionId, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}

		boolean hasUserAccess = TracerUtil.checkUserAccess(userId, "inviteUser");
		if (!hasUserAccess) {
			return ok(TracerUtil.InvalidAccessResponse());
		}
		UserService userService = (UserServiceImpl) serviceFactory.getInstance(Constants.USER_SERVICE);
		boolean response = userService.addUserToProject(assignUserId, projectId);
		if (response) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}

	/**
	 * this method is called when admin invite user , that user will get link to
	 * complete registration.
	 * 
	 * @return Result
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
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		boolean hasUserAccess = TracerUtil.checkUserAccess(userId, "inviteUser");
		if (!hasUserAccess) {
			return ok(TracerUtil.InvalidAccessResponse());
		}
		UserService userService = (UserServiceImpl) serviceFactory.getInstance(Constants.USER_SERVICE);
		boolean response = userService.inviteUser(email);
		if (response) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}

	/**
	 * this method is used to register new user .
	 * 
	 * @return Result
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
		UserService userService = (UserServiceImpl) serviceFactory.getInstance(Constants.USER_SERVICE);
		boolean response = userService.registration(user);
		if (response) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}

	/**
	 * this method will create new mile stone.
	 * 
	 * @return Result
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
		Session userSession = TracerUtil.checkSession(sessionId, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}

		Project project = Ebean.createQuery(Project.class).where().eq("id", projectId).findUnique();
		if (project == null) {
			return ok(TracerUtil.InvalidDataResponse());
		}
		UserDao userDao = (UserDaoImpl) DaoFactory.getInstance(Constants.USER_DAO);
		if (Constants.mileStoneSatus.active.toString().equalsIgnoreCase(status)) {
			boolean isActive = userDao.isMileStoneActive(projectId);
			if (isActive) {
				return ok(TracerUtil.ActiveMileStone());
			}
		}
		UserService  service =(UserServiceImpl) serviceFactory.getInstance(Constants.USER_SERVICE);
		boolean response = service.createMileStone(name, status, ended, project);
		if (response) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}

	/**
	 * this api will provide current mileStone based on project id,other
	 * information like severity , complexity,phase,ticket type
	 * 
	 * @return Result
	 */
	public static Result provideMasterData() {
		String sessionId = null;
		int projectId = 0;
		long userId;
		JsonNode json = request().body().asJson();
		try {
			sessionId = json.get(JsonKey.SESSION).asText();
			projectId = json.get(JsonKey.PROJECT_ID).asInt();
			userId = json.get(JsonKey.USER_ID).asLong();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(sessionId, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}

		UserService userService = (UserServiceImpl) serviceFactory.getInstance(Constants.USER_SERVICE);
		MasterDataBean masterDataBean = userService.getMasterData(projectId);
		if (masterDataBean != null) {
			return ok(TracerUtil.successResponse(masterDataBean));
		}
		return ok(TracerUtil.failureResponse());
	}

	/**
	 * this api will provide all user details.
	 * 
	 * @return Result
	 */
	public static Result getUserDetails() {
		String sessionId = null;
		long userId = 0l;
		JsonNode json = request().body().asJson();
		try {
			sessionId = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(sessionId, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		UserService userService = (UserServiceImpl) serviceFactory.getInstance(Constants.USER_SERVICE);
		List<User> users = userService.getallUser(userId);
		if (users != null) {
			return ok(TracerUtil.successResponse(users));
		}
		return ok(TracerUtil.failureResponse());
	}

}
