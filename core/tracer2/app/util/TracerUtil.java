/**
 * 
 */
package util;

import java.util.Random;

import models.Session;

import org.codehaus.jackson.node.ObjectNode;

import com.avaje.ebean.Ebean;

import play.libs.Json;

/**
 * @author Manzarul.Haque
 *
 */
public class TracerUtil {
	private static final  String className = TracerUtil.class.getName();
	/**
	 * this method is used to generate user session id.
	 * 
	 * @param email
	 *            user email id.
	 * @return String session id value.
	 */
	public static String getUniqueId(String email) {
		char[] data = { '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', '4', 'i',
				'j', 'k', '5', 'l', 'm', 'n', 'o', 'p', 'q', '6', 'A', 'B',
				'C', 'D', '9', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', '8',
				'M', 'N', 'O', 'P', 'Q', 'R', '7', 'S', 'T', 't', 'u', 'U',
				'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z', '1', '2', '3' ,'*','!','^','~'};
		String [] arrray = {"se","xp","lm","ux","tz","xm","mx","po","ty","me","emc","lmt","kro","uyz","erwz","oytz"};
		char [] charEmail = email.toCharArray();
		Random random = new Random();
		email = email.replace('.', data[random.nextInt(data.length)]);
		email = email.replace('@', data[random.nextInt(data.length)]);
		email = email.replace("com", arrray[random.nextInt(arrray.length)]);
		email = email.replace("in",  arrray[random.nextInt(arrray.length)]);
		StringBuilder builder = new StringBuilder();
		String str = System.currentTimeMillis() + random.nextInt() + "";
		String value = random.nextInt() + "";
		builder.append(value.substring(1, value.length() / 2));
		builder.append(charEmail[random.nextInt(charEmail.length)]);
		builder.append(str.substring(str.length() - 2) + ""
				+ str.substring(0, str.length() - 2));
		builder.append(charEmail[random.nextInt(charEmail.length)]);
		for (int i = 0; i < 4; i++) {
			builder.append(random.nextInt(data.length));
		}
		TrackLogger.debug(builder.toString(),className);
		return builder.toString();
	}
    
	
	/**
	 * this method will crate invalid session response as json and send it to
	 * controller.
	 * 
	 * @return
	 */
	public static ObjectNode invalidSessionResponse() {
		ObjectNode response = Json.newObject();
		response.put(JsonKey.STATUS_CODE,
				ResponseCode.InvalidSession.getErrorCode());
		response.put(JsonKey.STATUS_MESSAGE,
				ResponseCode.FAILURE.getErrorMessage());
		response.put(JsonKey.ERROR_MESSAGE,
				ResponseCode.InvalidSession.getErrorMessage());
		TrackLogger.debug(response.toString(),className);
		return response;
	}

	/**
	 * this method will crate invalid access response as json and send it to
	 * controller.
	 * 
	 * @return ObjectNode object.
	 */
	public static ObjectNode InvalidAccessResponse() {
		ObjectNode response = Json.newObject();
		response.put(JsonKey.STATUS_CODE,
				ResponseCode.RoleAccessNotFound.getErrorCode());
		response.put(JsonKey.STATUS_MESSAGE,
				ResponseCode.FAILURE.getErrorMessage());
		response.put(JsonKey.ERROR_MESSAGE,
				ResponseCode.RoleAccessNotFound.getErrorMessage());
		return response;
	}


	/**
	 * this method will crate invalid access response as json and send it to
	 * controller.
	 * 
	 * @return ObjectNode object.
	 */
	public static ObjectNode InvalidDataResponse() {
		ObjectNode response = Json.newObject();
		response.put(JsonKey.STATUS_CODE,
				ResponseCode.InCorrectData.getErrorCode());
		response.put(JsonKey.STATUS_MESSAGE,
				ResponseCode.FAILURE.getErrorMessage());
		response.put(JsonKey.ERROR_MESSAGE,
				ResponseCode.InCorrectData.getErrorMessage());
		return response;
	}
	
	
	/**
	 * this method will crate failure response and send to 
	 * controller.
	 * 
	 * @return ObjectNode object.
	 */
	public static ObjectNode failureResponse() {
		ObjectNode response = Json.newObject();
		response.put(JsonKey.STATUS_CODE,
				ResponseCode.FAILURE.getErrorCode());
		response.put(JsonKey.STATUS_MESSAGE,
				ResponseCode.FAILURE.getErrorMessage());
		response.put(JsonKey.ERROR_MESSAGE,
				ResponseCode.FAILURE.getErrorMessage());
		TrackLogger.debug("sending data===>"+response, className);
		return response;
	}
	
	/**
	 * this method will crate success response and send to 
	 * controller.
	 * @param obj
	 * @return ObjectNode object.
	 */
	public static ObjectNode successResponse(Object obj) {
		ObjectNode response = Json.newObject();
		response.put(JsonKey.STATUS_CODE,
				ResponseCode.Success.getErrorCode());
		response.put(JsonKey.STATUS_MESSAGE,
				ResponseCode.Success.getErrorMessage());
		response.put(JsonKey.ERROR_MESSAGE,"");
		if(obj != null){
			response.put(JsonKey.RESPONSE_DATE, Json.toJson(obj));
		}
		TrackLogger.debug("sending data ===>"+response, className);
		return response;
	}
	
	
	/**
	 * this method will crate success response and send to 
	 * controller.
	 * 
	 * @return ObjectNode object.
	 */
	public static ObjectNode successResponse() {
		ObjectNode response = Json.newObject();
		response.put(JsonKey.STATUS_CODE,
				ResponseCode.Success.getErrorCode());
		response.put(JsonKey.STATUS_MESSAGE,
				ResponseCode.Success.getErrorMessage());
		response.put(JsonKey.ERROR_MESSAGE,"");
			response.put(JsonKey.RESPONSE_DATE, "");
		TrackLogger.debug("sending data ===>"+response, className);
		return response;
	}
	
	
	/**
	 * this method will crate un Authorize  response as json and send it to
	 * controller.
	 * 
	 * @return ObjectNode object.
	 */
	public static ObjectNode unAuthorisedResponse() {
		ObjectNode response = Json.newObject();
		response.put(JsonKey.STATUS_CODE,
				ResponseCode.UnAuthorised.getErrorCode());
		response.put(JsonKey.STATUS_MESSAGE,
				ResponseCode.FAILURE.getErrorMessage());
		response.put(JsonKey.ERROR_MESSAGE,
				ResponseCode.UnAuthorised.getErrorMessage());
		return response;
	}
	/**
	 * this method is used to check user have access 
	 * of particular action or not
	 * @param userId
	 * @param action
	 * @return
	 */
	public static boolean  checkUserAccess(long userId,String action){
		return true;
	}
	
	/**
	 * main method.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getUniqueId("manzarul07@gmail.com"));
	}

	/**
	 * this method will check incoming value is null or empty.
	 * if value is either null or empty then it will provide true else false.
	 * @param value String
	 * @return boolean
	 */
	public static boolean checkNullOrEmpty(String value) {
		if (value == null || "".equalsIgnoreCase(value.trim()))
			return true;
		return false;
	}
	/**
	 * this method will check user session. if session found in db then 
	 * session object will be returned else null.
	 * @param sessionId session id.
	 * @param userId  long user id
	 * @return  Session object
	 */
	public static Session checkSession(String sessionId, long userId) {
		return Ebean.createQuery(Session.class).where().eq("sessionId", sessionId).eq("user_id", userId).findUnique();
	}
	
}
