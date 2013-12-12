package util;


/**
 * Created with IntelliJ IDEA.
 * User: Manzarul.Haque
 * Date: 5/21/13
 * Time: 11:27 AM
 * this class contains all error codes with their messages.
 */
public enum ResponseCode {
	/**
	 * UnAuthorised
	 */
UnAuthorised(Constants.UNAUTHORIZED_ID,Constants.UNAUTHORIZED),
/**
 * Success
 */
Success(Constants.SUCCESS_ID,Constants.SUCCESS),
/**
 * EmailExist
 */
EmailExist(Constants.EMAIL_EXISTS_ID, Constants.EMAIL_EXIST),
/**
 *InternalError 
 */
InternalError(Constants.INTERNAL_ERROR_ID, Constants.PROCESS_FAIL),
/**
 * InvalidSession
 */
InvalidSession(Constants.INVALID_SESSION_ID, Constants.INVALID_SESSION),
/**
 * InCorrectData
 */
InCorrectData(Constants.DATA_ERROR_ID,Constants.DATA_ERROR),
/**
 *FAILURE 
 */
FAILURE(Constants.FAILURE_ID,Constants.PROCESS_FAIL),
/**
 * BADREQUEST
 */
BADREQUEST(Constants.BAD_REQUEST_ID,Constants.BAD_REQUEST),
/**
 * RoleAccessNotFound
 */
RoleAccessNotFound(Constants.ROLE_AUTHORIZATION_FAILED_ID,Constants.AUTHORIZATION_FAILED),
/**
 *changePassword 
 */
changePassword(Constants.CHANGE_PASSWORD_ID,Constants.CHANGE_PASSWORD_SUCCESS);

    /**
     * error code contains int value
     */
    private int errorCode;
    /**
     * errorMessage contains proper error message.
     */
    private String errorMessage;

    /**
     * @param errorCode
     * @param errorMessage
     */
    private ResponseCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    /**
     * 
     * @param errorCode
     * @return
     */
    public String getMessage(int errorCode) {
        return "";
    }

    /**
     * @return
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
