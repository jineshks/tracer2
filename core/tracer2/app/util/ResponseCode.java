package util;


/**
 * Created with IntelliJ IDEA.
 * User: Manzarul.Haque
 * Date: 5/21/13
 * Time: 11:27 AM
 * this class contains all error codes with their messages.
 */
public enum ResponseCode {
UnAuthorised(Constants.UNAUTHORIZED_ID,Constants.UNAUTHORIZED),
Success(Constants.SUCCESS_ID,Constants.SUCCESS),
PhoneExist(Constants.PHONE_EXISTS_ID, Constants.PHONE_EXIST),
EmailExist(Constants.EMAIL_EXISTS_ID, Constants.EMAIL_EXIST),
PaymentFailed(Constants.AUTH_NET_FAILURE_ID, Constants.AUTH_NET_FAILURE),
PromoCodeAndPaymentBothIncorrect(Constants.PAYMENT_AND_PROMO_FAILURE_ID, Constants.PAYMENT_AND_PROMO_FAILURE),
InternalError(Constants.INTERNAL_ERROR_ID, Constants.PROCESS_FAIL),
InvalidPromoCode(Constants.PROMO_CODE_INVALID_ID, Constants.PROMO_CODE_INVALID),
InvalidSession(Constants.INVALID_SESSION_ID, Constants.INVALID_SESSION),
HasPassengerReservation(Constants.PASS_REG_MESSAGE_ID, Constants.PASS_REG_MESSAGE),
IsCurrentReservation(Constants.CURRENT_ID,Constants.CURRENT),
IsAdvanceReservation(Constants.ADVANCE_ID,Constants.ADVANCE),
NoDriverFound(Constants.NO_DRIVER_FOUND_ID,Constants.NO_DRIVER_FOUND),
PaymentUpdateFailure(Constants.PAYMENT_UPDATE_FAILED_ID,Constants.PAYMENT_UPDATE_FAILED),
InCorrectData(Constants.DATA_ERROR_ID,Constants.DATA_ERROR),
FavouriteAddFailure(Constants.FAVOURITE_ALREADY_ADDED_ID,Constants.FAVOURITE_ALREADY_ADDED),
FavouriteAddedSuccess(Constants.FAVOURITE_ADDED_ID,Constants.FAVOURITE_ADDED),
FavouriteRemoved(Constants.FAVOURITE_REMOVED_ID,Constants.PROCEES_FAILED),
FavouriteRemovedFailure(Constants.FAVOURITE_REMOVED_FAILURE_ID,Constants.PROCEES_FAILED),
GetFavourite(Constants.NO_FAVOURITE_ID,Constants.NO_FAVOURITE),
UpdateFavorite(Constants.UPDATE_FAVOURITE_SUCCESS_ID,Constants.UPDATE_SUCCESS),
UpdateFavoriteFailure(Constants.UPDATE_FAVOURITE_FAILED_ID,Constants.UPDATE_FAILED),
FAILURE(Constants.FAILURE_ID,Constants.PROCESS_FAIL),
RESERVATIONDATEINVALID(Constants.INVALID_RESERVATION_DATE_ID,Constants.INVALID_RESERVATION_DATE),
PAYMENTFAILURE(Constants.PAYMENT_FAILURE_ID,Constants.PAYMENT_FAILURE),
FOUND(Constants.FOUND_ID,Constants.FOUND),
NOTFOUND(Constants.NOT_FOUND_ID,Constants.NOT_FOUND),
RoleAccessNotFound(Constants.ROLE_AUTHORIZATION_FAILED_ID,Constants.AUTHORIZATION_FAILED),
ALREADYHAVERESRVATION(Constants.TRIP_ALREADY_PRESENT_ID,Constants.TRIP_ALREADY_PRESENT),
INVALIDDATE(Constants.INVALID_DATE_ID,Constants.INVALID_DATE),
CANCELLTRIP(Constants.CANCEL_TRIP_ERROR_ID,Constants.CANCEL_TRIP_ERROR),
BADREQUEST(Constants.BAD_REQUEST_ID,Constants.BAD_REQUEST),
TRIPUNAVAILABE(Constants.TRIP_UNAVAILABLE_ID,Constants.DRIVER_TRIP_UNAVAILABLE),
REMOVETRIP(Constants.TRIP_REMOVAL_ERROR_ID,Constants.TRIP_REMOVAL_ERROR),
EARLYCANCELMESSAGE(Constants.DRIVER_EARLY_TRIP_CLOSE_ERROR_ID,Constants.DRIVER_EARLY_TRIP_CLOSE_ERROR),
TRIPALREADYBEGUN(Constants.ACTIVE_TRIP_CANCEL_ERROR_ID,Constants.ACTIVE_TRIP_CANCEL_ERROR),
SOCIAL_LOGIN_PHONE_EXIST(Constants.SOCIAL_LOGIN_PHONE_ERROR_ID,Constants.SOCIAL_LOGIN_PHONE_ERROR),
DriverRegistrationSuccess(Constants.DRIVER_REGISTRATION_SUCCESS_MESSAGE_ID,Constants.DRIVER_REGISTRATION_SUCCESS_MESSAGE),
ProfileUpdate(Constants.PROFILE_UPDATE_SUCCESS_MESSAGE_ID,Constants.PROFILE_UPDATE_SUCCESS_MESSAGE),
PassengerRegistrationSuccess(Constants.PASSENGER_REGISTRATION_SUCCESS_MESSAGE_ID,Constants.PASSENGER_REGISTRATION_SUCCESS_MESSAGE),
HasPassengerCurrentOPenReservation(Constants.PASSENGER_CURRENT_BOOKING_REQUEST_ERROR_ID,Constants.PASSENGER_CURRENT_BOOKING_REQUEST_ERROR),
FutureReservationNotAccepted(Constants.FUTURE_RESERVATION_NOT_ACCEPTED_ID,Constants.FUTURE_RESERVATION_NOT_ACCEPTED),
changePassword(Constants.CHANGE_PASSWORD_ID,Constants.CHANGE_PASSWORD_SUCCESS),
RemoveTagMessage(Constants.DISPATCHER_REMOVE_TAG_ERROR_ID,Constants.DISPATCHER_REMOVE_TAG_ERROR),
DriverAlreadyAcceptedActiveTrip(Constants.DRIVER_ACCEPT_TRIP_ERROR_ID,Constants.DRIVER_ACCEPT_TRIP_ERROR),
BookingCanceled(Constants.TRIP_UNAVAILABLE_ID,Constants.BOOKING_CANCELED_ERROR),
TagMessageFailure(Constants.TAG_MESSAGE_FAILURE,Constants.TAG_MESSAGE_FAILURE_ERROR);

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
