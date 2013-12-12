/**
 * 
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
    public static final String PHONE_EXIST = "Phone number is already registered with another cab hound account";
    public static final String EMAIL_EXIST = "email already exist";
    public static final String PROMO_CODE_INVALID = "Invalid promo code";
    public static final String AUTH_NET_FAILURE = "Payment account failure";
    public static final String CUSTOMER_TYPE_PASS = "passenger";
    public static final String CUSTOMER_TYPE_DRIVER = "driver";
    public static final String PAYMENT_AND_PROMO_FAILURE = "Promo code and payment type are incorrect";
    public static final String PROCESS_FAIL = "Process failed, Please try again.";
    public static final String MAIlSUBJECT = "welcome to cab hound";
    public static final String MAIlSUBJECTFORDISPATCHE = "welcome to web dispatcher";
    public static final String FORGOT_PASSWORD_SUCCESS = "Please check your email for password.";
    public static final String INVALID_SESSION = "Invalid session";
    public static final String USER_DAO = "user";
    public static final String RESERVATION_DAO = "reservation";
    public static final String SETTING_DAO = "setting";
    public static final String TRIP_DAO = "tripdao";
    public static final String DRIVER_ACCEPT_PUSH_MESSAGE = "Your trip request is accepted";
    public static final String DRIVER_UPDATE_PUSH_MESSAGE = "From driver: I am here";
    public static final int cutOffTime = 30;
    public static final int DEFAULT_LOCATION_UPDATE_TIME = 20;
    public static final String PASS_REG_MESSAGE = "you already have a reservation with in 30 minutes";
    public static final String
            GOOGLE_API_Key = "ABQIAAAAoner8BZDkqFQeKEwHcRAJhT2yXp_ZAY8_ufC3CFXhHIE1NvwkxQm36f3blUWvs1baUIvNtRBNv80IA";
    //in km
    public static final int CURRENT_RESERVATION_DISTANCE_CHECK = 15;
    public static final String OP_TYPE_REMOVE = "remove";
    public static final String OP_TYPE_OFF = "off";
    public static final String OP_TYPE_UPDATE = "update";
    public static final String OP_TYPE_ADD ="add";
    public static final String FIND_CAB_REQUEST = "61";
    public static final int DEFAULT_NOTIFICATION_COUNT = 0;
    public static final String ADVANCE_RESERVATION = "advance";
    public static final String RESERVATION_SOURCE = "app";
    public static final String NEW_RESERVATION = "There is a new reservation in the area";
    public static final String IPHONE_DEVICE_NAME = "ch_iPhone";
    public static final String IPHONE_ACTIONLOCKEY = "Open App";
    public static final String IPHONE_RINGER_SOUND = "longSound.wav";
    public static final String IPHONE_SOUND = "short.wav";
   // public static final String IPHONE_REQUEST_URI = "gateway.push.apple.com";
    public static final String IPHONE_REQUEST_URI = "gateway.sandbox.push.apple.com";
    public static final String IPHONE_PORT = "2195";
    public static final String IPHONE_KEY_PASS = "changeit";

    public static final String UPDATE_CLIENT_AUTH = "Update-Client-Auth";

    public static final String PARAM_REGISTRATION_ID = "registration_id";

    public static final String PARAM_DELAY_WHILE_IDLE = "delay_while_idle";

    public static final String PARAM_COLLAPSE_KEY = "collapse_key";

    public static final String UTF8 = "UTF-8";
    public static final String ANDORIED_APP_ID = "AIzaSyARBYb_ZdtVAgqdTu4meNAzY2KW9khtN4g";
    public static final String IPHONE_DRIVER_PUSH = "DriverPushDevCertificate.p12";
    public static final String IPHONE_PASSENGER_PUSH = "PassengerDevPushCertificate.p12";
    public static final String FAVOURITE_OP_ADD = "add";
    public static final String FAVOURITE_OP_REMOVE = "remove";
    public static final String FAVOURITE_OP_GET = "get";
    public static final String FAVOURITE_OP_UPDATE = "update";
    public static final String DRIVER_SEARCH_MESSAGE = "No drivers found with preferences ,try with these.";
    public static final String INVALID_RESERVATION_DATE = "One month future reservation allowed.";
    //public static  final String IPHONE_PUSH_FILE_PATH="D:\\cabhoundRedesign\\public\\pushFile\\";
    public static  final String IPHONE_PUSH_FILE_PATH="/home/cabhound/";

    public static final String PASSENGER_WILL_NOT_PAY_WITH_CABHOUND = "Rate your passenger";
    public static final String  PAYMENT_FAILURE = "Payment failed, Pay directly to driver.";
    public static enum TicketStatus {
        active, pending, done;
    }

    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String OLD_PASSWORD = "oldPass";
    public static final String NEW_PASSWORD = "newPass";
    public static final String USER_TYPE = "userType";
    public static final String DEVICE_TYPE = "deviceType";
    public static final String DEVICE_ID = "deviceId";
    public static final String TIMEZONE = "timeZone";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String SOCIAL_LOGIN = "socialLogin";
    public static final String DRIVER_TYPE = "driver";
    public static final String INVALID_LOGIN = "73";
    public static final String SUCCESS_LOGIN = "83";
    public static final String SESSION ="sessionId";
    public static final String STATUS_CHANGE ="status";
    public static final String INTERNAL_ERROR="5";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String VEHICLE_FEATURE = "vehicleFeature";
    public static final String LICENCE_NUMBER = "licenceNumber";
    public static final String ADDRESS_ONE= "addressOne";
    public static final String ADDRESS_TWO = "addressTwo";
    public static final String CITY = "city";
    public static final String STATE ="state";
    public static final String ZIP_CODE= "zip_code";
    public static final String PHONE_NUMBER= "phoneNumber";
    public static final String SUBSCRIPTION_TYPE= "subscriptionType";
    public static final String CARD_NUMBER = "cardNumber";
    public static final String CVN_NUMBER = "cvnNumber";
    public static final String EXPIRY_DATE = "expDate";
    public static final String PAYMENT_TYPE ="paymentType";
    public static final String PROMO_CODE = "promoCode";
    public static final String VEHICLE_TYPE = "vehicleType";
    public static final String CHANGE_PASSWORD = "changePassword";
    public static final String DRIVER_PROFILE_UPDATE = "update";
    public static final String START_INDEX ="startIndex";
    public static final String END_INDEX ="endIndex";
    public static final String DUTY_STATUS_CHANGE ="driverStatus";
    public static final String OPERATION_TYPE ="operationType";
    public static final String DRIVER_BALANCE ="balance";
    public static final String DRIVER_UPDATE_LOCATION ="locationUpdate";
    public static final String DRIVER_BOOLEAN ="boolean";
    public static final String DRIVER_City ="workingCity";
    public static final String SMS_OPT_INFO ="smsOptInfo";
    public static final String LAT_LONG ="latLong";
    public static final String PASSENGER_REQUEST_INFO ="request";
    public static final String DRIVER_ACCEPT_REQUEST ="acceptRequest";
    public static final String DRIVER_REACHED_LOCATION ="driverReachedLocation";
    public static final String DRIVER_REJECT_PASSENGER ="driverRejectPassenger";
    public static final String DRIVER_OUT_OF_RANGE ="driverOutOfRange";
    public static final String DRIVER_BEGIN_TRIP ="beginTrip";
    public static final String DRIVER_END_TRIP ="driverEndTrip";
    public static final String DRIVER_SENDING_BILL ="driverSendingBill";
    public static final String TRIP_ID ="tripId";
    public static final String DRIVER_PHONE ="driverNumber";
    public static final String BOUNTY ="bounty";
    public static final String ETA ="eta";
    public static final String PASSENGER_EMAIL ="passengerEmail";
    public static final String  AMOUNT ="amount";
    public static final String  OS ="os";
    public static final String  OS_VERSION ="osVersion";
    public static final String  DESTINATION_ADDRESS ="destinationAddress";
    public static final String  DESTINATION_LATITUDE ="destinationLatitude";
    public static final String  LATITUDE_LONGITUDE ="latitudeLongitude";
    public static final String  DESTINATION_LONGITUDE ="destinationLongitude";
    public static final String  PASSENGER_RATING ="passengerRating";
    public static final String  RATING ="driverRating";
    public static final String  PASSENGER_NOT_SENDING_BILL ="noBill";
    public static final String  PASSENGER_SENDING_BILL ="sendBill";
    public static final String  DRIVER_EMAIL ="driverEmail";
    public static final String  BILL ="billAmount";
    public static final String  TIP_AMOUNT ="tip";
    public static final int N0_OF_DAYS_CHECK = 10;
    public static final int N0_OF_TRIP_CHECK = 10;
    public static final int USER_BLOCKED=123;

    public static final String DRIVER_SUBSCRIPTION_AMOUNT="25";
    public static final String DRIVER_SUBSCRIPTION_AMOUNT1="50";

    public static final String  TRIP_UNAVAILABLE = "0|79";

    public static final String COUNTRY_CODE = "+1";
    public static final String LOCAL_COUNTRY_CODE = "+91";
    public static  final String DRIVER_BEGIN_TRIP_MESSAGE = "The trip has started";
    public static  final int DRIVER_SUBSCRIPTION_PERFARE_CHARGE=2;
    public static final int DEFAULT_DRIVER_RATING = 2;
    public static final int DEFAULT_PASSENGER_RATING = 2;
    public static final boolean PREPAY_ALWAYS_SUCCESS=true;
    public static final int PREPAY_AMOUNT_VALUE=25;
    public static final String RECEIPT_GENERATION_DATE_FORMAT = "EEEE, MMMM dd, yyyy";
    public static final String ADMIN_EMAIL="admin@appedgy.com";
    public static  final String OPERATION_TYPE_CANCEL_TRIP = "cancelTrip";
    public static  final String OPERATION_TYPE_BEGIN_TRIP = "beginTrip";
    public static  final String OPERATION_TYPE_END_TRIP = "endTrip";
    public static final String TRIP_STATUS= "waiting for driver.";
    public static final String RATING_TEXT= "rating";
    public static final String TRIP_ROUTES = "route";
    public static final String OPERATION_TYPE_DRIVER_ARRIVED  = "Arrive";

    //driver status message
    public static final String DRIVER_AGREED = "Your cab is on the way";
    public static final String DRIVER_REJECT_PASSENGER_MESSAGE = "Request rejected";
    public static final  String DRIVER_REACHED_LOCATION_MESSAGE ="Your cab has arrived";
    public static final String  DRIVER_BEGIN_TRIP_MESS ="On trip";
    public static final String  DRIVER_CLOSE_TRIP ="Trip complete";
    public static final String  DRIVER_RATING ="Driver rating";
    public static final String  DRIVER_CONFIRM_BILL ="Driver confirm bill";
    public static  final String PASSENGER_NO_BILL ="Passenger no bill";
    public static  final String PASSENGER_WAITING_FOR_BILL ="Waiting for bill";
    public static  final String DRIVER_SENDING_NO_BILL ="Driver sending no bill";
    public static final String  DRIVER_SEND_BILL = "Driver sending bill";
    public static final String PASSENGER_CONFIRM_BILL  = "Passenger confirm bill";
    public static final String RESERVATION_CANCEL_MESSAGE = "Sorry, Reservation is cancelled by  ";
    public static final String PASSENGER_RATE = "Passenger rating";
    public static final String DRIVER_REJECT_PASS  ="Driver reject passenger request";
    public static final int DRIVER_DEFAULT_RATING =2;
    public static final int PASSENGER_DEFAULT_RATING =2;
    public static final String INVALID_TRIP = "Trip is no longer available";
    public static final String YAHOO_APPID="3AKMvJXV34En2Fkh8ptQdnC79MH.Pvz96SkNa6q2j_wuF7uS1MZ.aOTozRZSEQ--";
    public static final String YAHOO_QUERY="Taxi%20Services";
    public static final byte EMAIL_DUPLICATE = 69;
    public static final byte DUPLICATE_ENTRY = 122;
    public static final byte PROMO_CODE_ERROR = 75;
    /*The Constant ACCOUNT_SID. Find it at twilio.com/user/account 
     */
    public static final String  TWILIO_ACCOUNT_SID="AC13fcb98d1450ad2f2b7e0554007bcf66";
    /*
     * The Constant AUTH_TOKEN. Find it at twilio.com/user/account
     */
    public static final String TWILIO_AUTH_TOKEN="0c4c7d6789bd0ae8a4eb1842b6323c81";
    public static final String  TWILIO_FROM_NO="+12407884894";
    //dispatcher
    public static final String FOUND="found";
    public static final String NOT_FOUND="notFound";
    public static final String USER_COUNTRY_CODE="+91";
    public static final String FORGOT_PASSWORD_MAIL_SUBJECT = "Cab Hound credentials.";
    /**
     * CITY_UPDATE.
     */
    public static final String CITY_UPDATE="cityUpdate";
    /**
     * VEHICLE_UPDATE.
     */
    public static final String VEHICLE_UPDATE="vehicleUpdate";
    /**
     * BOTH.
     */
    public static final String BOTH="both";
    /**
     *BOOKING_STATUS_ACCEPT.
     */
    public static final String BOOKING_STATUS_ACCEPT = "A";
    
    /**
     *BOOKING_STATUS_OPEN.
     */
    public static final String BOOKING_STATUS_OPEN = "O";
    /**
     * TIME ZONE
     */
    public static final String TIME_ZONE = "UTC";
    /**
     * TRIP_STATUS_CANCELLED.
     */
    public static final String TRIP_STATUS_CANCELLED = "C";
    /**
     * TRIP_STATUS_ACTIVE.
     */
    public static final String TRIP_STATUS_ACTIVE = "A";
    /**
     * TRIP_STATUS_SCHEDULED
     */
    public static final String TRIP_STATUS_SCHEDULED = "S";
    /**
     * MESSAGE_CURRENT.
     */
    public static final String PUSH_CURRENT = "current";
    /**
     * MESSAGE_ALL.
     */
    public static final String PUSH_ALL = "all";
    public static final int  MESSAGE_READ_STATUS = 1;
    public static final int MESSAGE_UNREAD_STATUS  =0;
    public static final int MESSAGE_DELETE_STATUS = -1;
    public static final int ADVANCE_RESERVATION_DISPLAY_MAX_TIME = 30;


    public static final String SUCCESS = "Success";
    public static final String ADVANCE = "advance";
    public static final String NO_DRIVER_FOUND = "No drivers matching your request are available at the moment. Please try again later.";
    public static final String PAYMENT_UPDATE_FAILED = "Payment update failed";
    public static final String DATA_ERROR = "Please check the data";
    public static final String FAVOURITE_ALREADY_ADDED = "This location is already present in your favorites";
    public static final String CURRENT = "current";
    public static final String FAVOURITE_ADDED = "Location added to favorites";
    public static final String PROCEES_FAILED = "Process failed";
    public static final String NO_FAVOURITE = "You do not have any favorites";
    public static final String UPDATE_SUCCESS = "Updated successfully.";
    public static final String UPDATE_FAILED = "Update failed.";
    public static final String AUTHORIZATION_FAILED = "You Don't have the access.";
    public static final String TRIP_ALREADY_PRESENT = "you already have a reservation with in 30 minutes";
    public static final String INVALID_DATE = "Please check reservation date.";
    public static final String CANCEL_TRIP_ERROR = "Error in cancelled trip";
    public static final String BAD_REQUEST = "Bad request";
    public static final String DRIVER_TRIP_UNAVAILABLE = "Trip is taken by other driver";
    public static final String TRIP_REMOVAL_ERROR = "You can't remove accepted trip.";
    public static final String DRIVER_EARLY_TRIP_CLOSE_ERROR = "Trip can't be ended within 3 minutes.";
    public static final String ACTIVE_TRIP_CANCEL_ERROR = "Sorry, trip has already started.";
    public static final String SOCIAL_LOGIN_PHONE_ERROR = "Phone number is already associated with another email address.";
    public static final String DRIVER_REGISTRATION_SUCCESS_MESSAGE = "Thank you for registering with Cab Hound. Your account is currently under review. You will receive a confirmation as soon as it is approved.";
    public static final String PROFILE_UPDATE_SUCCESS_MESSAGE = "Your profile was updated successfully.";
    public static final String PASSENGER_REGISTRATION_SUCCESS_MESSAGE = "Thank you for registering with Cab Hound. You will receive your account information on your phone/email shortly.";
    public static final String PASSENGER_CURRENT_BOOKING_REQUEST_ERROR = "You already have current booking, Please cancel to make a new booking.";
    public static final String CHANGE_PASSWORD_SUCCESS = "Your password was updated successfully.";
    public static final String DISPATCHER_REMOVE_TAG_ERROR = "Please tag Off driver, before removing the tag.";
    public static final String DRIVER_ACCEPT_TRIP_ERROR = "You already have current booking, Please complete trip to accept new bookings.";
    public static final String BOOKING_CANCELED_ERROR = "Sorry, booking is cancelled.";
    public static final String TAG_MESSAGE_FAILURE_ERROR = "Tag message will work if any driver is assign with this tag.";
    
    public static final String UNAUTHORIZED = "You are not authorize to perform this task";
    public static final int UNAUTHORIZED_ID = 401;
    public static final int SUCCESS_ID = 200;
    public static final int PHONE_EXISTS_ID = 300;
    public static final int EMAIL_EXISTS_ID = 301;
    public static final int AUTH_NET_FAILURE_ID = 302;
    public static final int PAYMENT_AND_PROMO_FAILURE_ID = 303;
    public static final int INTERNAL_ERROR_ID = 304;
    public static final int PROMO_CODE_INVALID_ID = 305;
    public static final int INVALID_SESSION_ID = 306;
    public static final int PASS_REG_MESSAGE_ID = 307;
    public static final int FAILURE_ID = 320;
    public static final int INVALID_RESERVATION_DATE_ID = 321;
    public static final int PAYMENT_FAILURE_ID = 322;
    public static final int FOUND_ID = 333;
    public static final int NOT_FOUND_ID = 334;
    public static final int ADVANCE_ID = 309;
    public static final int NO_DRIVER_FOUND_ID = 310;
    public static final int PAYMENT_UPDATE_FAILED_ID = 311;
    public static final int DATA_ERROR_ID = 312;
    public static final int FAVOURITE_ALREADY_ADDED_ID = 313;
    public static final int CURRENT_ID = 308;
    public static final int FAVOURITE_ADDED_ID = 314;
    public static final int FAVOURITE_REMOVED_ID = 315;
    public static final int FAVOURITE_REMOVED_FAILURE_ID = 316;
    public static final int NO_FAVOURITE_ID = 317;
    public static final int UPDATE_FAVOURITE_SUCCESS_ID = 318;
    public static final int UPDATE_FAVOURITE_FAILED_ID = 319;
    public static final int ROLE_AUTHORIZATION_FAILED_ID = 335;
    public static final int TRIP_ALREADY_PRESENT_ID = 336;
    public static final int INVALID_DATE_ID = 337;
    public static final int CANCEL_TRIP_ERROR_ID = 338;
    public static final int BAD_REQUEST_ID = 400;
    public static final int TRIP_UNAVAILABLE_ID = 339;
    public static final int TRIP_REMOVAL_ERROR_ID = 340;
    public static final int DRIVER_EARLY_TRIP_CLOSE_ERROR_ID = 341;
    public static final int ACTIVE_TRIP_CANCEL_ERROR_ID = 342;
    public static final int SOCIAL_LOGIN_PHONE_ERROR_ID = 343;
    public static final int DRIVER_REGISTRATION_SUCCESS_MESSAGE_ID = 344;
    public static final int PROFILE_UPDATE_SUCCESS_MESSAGE_ID = 345;
    public static final int PASSENGER_REGISTRATION_SUCCESS_MESSAGE_ID = 346;
    public static final int PASSENGER_CURRENT_BOOKING_REQUEST_ERROR_ID = 347;
    public static final int CHANGE_PASSWORD_ID = 348;
    public static final int DISPATCHER_REMOVE_TAG_ERROR_ID = 349;
    public static final int DRIVER_ACCEPT_TRIP_ERROR_ID = 350;
    public static final int BOOKING_CANCELED = 351;
    public static final int TAG_MESSAGE_FAILURE = 352;
    public static final String TIME_OUT = "Timed Out";
}
