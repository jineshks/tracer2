/**
 * 
 */
package util;


/**
 * @author Manzarul.Haque
 *
 */
public class Constants {
	/*
	 * 
	 */
    public static final String SALT = "a is salt simple clear this";
    public static final String SUCCESS_MESSAGE = "success";
    public static final String FAILURE_MSG = "failure";
    public static final String TRUE = "true";
    public static final String FAILURE_MESSAGE = "failure";
    public static final String EMAIL_EXIST = "email already exist";
    public static final String PROCESS_FAIL = "Process failed, Please try again.";
    public static final String INVALID_SESSION = "Invalid session";
    public static final String TIME_ZONE ="UTC";
    /**
     * ticket status may be    active, pending, closed;
     * @author Manzarul.Haque
     *
     */
    public static enum TicketStatus {
        active, pending, closed;
    }
    
   /**
    * mileStoneSatus may be 
    * active,closed,pending 
    * @author Manzarul.Haque
    *
    */
   public  enum mileStoneSatus {
	   active,closed,pending;
   }
    public static final String SUCCESS = "Success";
    public static final String DATA_ERROR = "Please check the data";
    public static final String AUTHORIZATION_FAILED = "You Don't have the access.";
    public static final String BAD_REQUEST = "Bad request";
    
    public static final String UNAUTHORIZED = "You are not authorize to perform this task";
    public static final String DEFAULT_MILE_STONE = "backlog";
    public static final int UNAUTHORIZED_ID = 401;
    public static final int SUCCESS_ID = 200;
    public static final int EMAIL_EXISTS_ID = 301;
    public static final int INTERNAL_ERROR_ID = 304;
    public static final int INVALID_SESSION_ID = 306;
    public static final int FAILURE_ID = 320;
    public static final int DATA_ERROR_ID = 312;
    public static final int ROLE_AUTHORIZATION_FAILED_ID = 335;
    public static final int BAD_REQUEST_ID = 400;
    public static final String SERVER_URL = "http://idc.tarento.com:9000/register";
}
