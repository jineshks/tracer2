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
    public static enum BalanceSource {
        promocode, reward, refund;
    }
    /**
     * 
     * @author Manzarul.Haque
     *O for open , CO for closed
     *E for expired. A for Active,
     *C for cancelled , EN for ENd,S for schedule
     *M for moved 
    *
     */
    public static enum TripStatus {
        O,CO, E, A, C,EN,S,M,;
    }
    /**
     * 
     * @author Manzarul.Haque
     * C for Current,  A for Advance.
     */
    public static enum TripType {
    	C, A;
//        Current("C"), Advance("A");
//        private String code;
//        private TripType(String code) {
//			this.code = code;
//		}
//        
//        public String toString(){
//        	return code;
//        }
    }
    public static enum PromoType {
        trip, percent, cash;
    }
    public static enum ReservationStatus {
        accepted,nAccepted,rejected,active,completed,expired,cancelled,closed,timeout;
    }
    /**
     * @author Manzarul.Haque
     *A for Accept , R for  reject
     *P for pending, T for timedOut
     *RC for reservation cancelled.
     *S for schedule  ,NA driver try to accept
     *trip but trip is taken by any other driver.
     */
    public static enum DriverReqStatus {
        A, R, P, T, RC, S,NA;
    }
    public static enum BountyStatus {
        withdrawn, accepted, selected, expired;
    }


    public static enum driverVersionType {
        ALPHA, BETA
    }

    public static enum Notifications { 
        passenger, driver, passdriver;
    }
   
    public static enum UserRole {
    	passenger,driver,agent,superAdmin,admin;
    }
    
    public static enum SubscriptionType {
    	unlimited,payPerFare;
    }
    /**
     * 
     * @author Manzarul.Haque
     *A for active , E for expire
     */
    public static enum MessageStatus {
    	A,E;
    }
    /**
     * 
     * @author Manzarul.Haque
     * U for unread , R for read , D for delete
     */
    public static enum UserAction {
    	U,R,D;
    }
    /**
     * this enum  says about the reservation source.
     * @author Manzarul.Haque
     *app for mobile and dispatcher for web
     */
    public static enum ReservationSource {
    	app,dispatcher;
    }
    
    public static enum DriverDutyStatus {
    	ON,OFF;
    }
    /**
     * this defines message type
     * @author Manzarul.Haque
     *A for Normal message
     *B for Airport messages
     *C for Street message
     */
    public static enum MessageType{
    	A,B,C;
    }
    /**
     * 
     * @author Manzarul.Haque
     *
     */
    public static enum AppType{
    	iphonePassenger,iphoneDriver,andoridPassenger,andoridDriver;
    }
    public static final String DRIVER_LOGIN_REQUEST_DATA ="session|email|requestId|requestId|email|password|deviceType|deviceId";

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

    /**
     * DriverBookingStatus enum.
     */
    public enum DriverBookingStatus{
        ACCEPTED("A" , "Accepted"),
        REQUESTED("R" , "Requested"),
        PENDING("P" , "Pending");

        private String key;
        private String name;

        DriverBookingStatus(String str, String desc) {
            key = str;
            name = desc;
        }

        public String toString() {
            return name;
        }

        public String getKey() {
            return key;
        }

        public static DriverBookingStatus getDriverBookingStatus(String key) {
            if (key.equals(ACCEPTED.getKey())) {
                return DriverBookingStatus.ACCEPTED;
            } else if (key.equals(REQUESTED.getKey())) {
                return DriverBookingStatus.REQUESTED;
            } else if (key.equals(PENDING.getKey())) {
                return DriverBookingStatus.PENDING;
            }
            return null;
        }
    }

    /**
     *  BOOKING_CANCELED_BY_DRIVER
     */
    public static  final String BOOKING_CANCELED_BY_DRIVER ="cancelled by driver ";
    /**
     *  BOOKING_CANCELED_BY_PASSENGER
     */
    public static  final String BOOKING_CANCELED_BY_PASSENGER ="cancelled by passenger ";
    /**
     * BOOKING_CANCELED_BY_ADMIN
     */
    public static  final String BOOKING_CANCELED_BY_ADMIN ="cancelled by admin dispatcher ";
    /**
     * BOOKING_CANCELED_BY_AGENT.
     */
    public static  final String BOOKING_CANCELED_BY_AGENT ="cancelled by agent dispatcher ";
    
    public static final String  ACCEPT_TRIP = "Accept";
    public static final String  DECLINE_TRIP = "Decline";
    public static final String  CANCEL_TRIP  ="Cancel";
    public static final String  REMOVE_TRIP = "Remove";
    public static final String  DRIVER_ACCEPT_MESSAGE = "Booking confirmed";
    public static final String  DRIVER_REACHED = "Your cab has arrived";
    public static final String  DRIVER_SENDING_BILL_AMOUNT = "View bill";
    public static final String  DRIVER_END_TRIP_PUSH = "Trip complete";
    public static final String  PASSENGER_CLOSE_TRIP = "Trip cancelled";
    public static final String  PASSENGER_END_TRIP = "Trip complete";

    public static final String  DRIVER_ACCEPT_BOOKING = "Booking accepted";
    public static final String DRIVER_NOT_ACCEPT_BOOKING = "Waiting for driver";    
    public static final String UPDATE_LOCATION_PUSH_MESSAGE = "update location";
    public static final String CURRENT_RESERVATION_MESSAGE = "New trip request";
    public static final String DRIVER_NOT_SENDING_BILL = "Please pay directly to driver";
    /*
     *ResendPsuhMessage 
     */
    public static String ResendPsuhMessage = "The selected drivers have not responded. Cab Hound will select the next best available drivers for you.";
    /*
	 * NO_DRIVER_ACCEPT_PUSH_MESSAGE push message for passenger.
	 */
	public static final String NO_DRIVER_ACCEPT_PUSH_MESSAGE = "Sorry, The selected drivers have not responded. Please make another request.";
	public static final String FUTURE_RESERVATION_NOT_ACCEPTED= "Sorry, No driver accept your request.";
    public static void main(String[] args) throws Exception{
    	 SimpleDateFormat dateFormat = new SimpleDateFormat(
                 "yyyy-MM-dd HH:mm:ss");
    	 String reservDate = "2013-08-16 06:02:00";
    	 dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
         Date reservDate1 = dateFormat.parse(reservDate);
         System.out.println("in America timeZOne==========>" + reservDate1);
         System.out.println("in America timeZOne==========>" + dateFormat.format(reservDate1));
         dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/kolkatta"));
        String  reservDate2 = dateFormat.format(reservDate1);
         System.out.println("in default timeZOne==========>" + reservDate2);
	}
    
    public static final String IPHONE_LIST ="iphoneList";
    public static final String IPHONE ="iphone";
    public static final String ANDROID_LIST ="androidList";
    public static final String ANDROID ="android";
    public static final String DRIVER_IPHONE ="driverIphone";
    public static final String DRIVER_ANDROID ="driverAndroid";
    public static final String PASSENGER_IPHONE ="passengerIphone";
    public static final String PASSENGER_ANDROID ="passengerAndroid";

    public static final String PUSH_SENDING_SOURCE_SCHEDULER = "scheduler";
    public static final String PUSH_SENDING_SOURCE_APP = "app";
    public static final int DRIVER_AGREED_CODE = 10;
    public static final int DRIVER_REACHED_LOCATION_MESSAGE_CODE = 11;
    public static final int DRIVER_BEGIN_TRIP_MESS_CODE = 12;
    public static final int DRIVER_CLOSE_TRIP_CODE = 13;
    public static final int DRIVER_SEND_BILL_CODE = 14;
    public static final int PASSENGER_CONFIRM_BILL_CODE = 15;
    public static final int PASSENGER_NO_BILL_CODE = 16;
    public static final int DRIVER_RATING_CODE = 17;
    public static final int PASSENGER_RATE_CODE = 18;
    public static final int DRIVER_SENDING_NO_BILL_CODE = 19;
    public static final int DRIVER_REJECT_PASS_CODE = 20;
    public static final int BOOKING_CANCELED_BY_DRIVER_CODE = 21;
    public static final int BOOKING_CANCELED_BY_PASSENGER_CODE = 22;
    public static final int PASSENGER_CLOSE_TRIP_CODE = 23;
    public static final int DRIVER_ACCEPT_BOOKING_CODE = 24;
    public static final int DRIVER_NOT_ACCEPT_BOOKING_CODE = 25;
    public static final int PASSENGER_END_TRIP_CODE = 26;
    public static final int PASSENGER_WAITING_FOR_BILL_CODE = 27;
    
    
    public static final int START_TRIP_PUSH_ID = 101;
    public static final int DRIVER_END_TRIP_PUSH_ID =102;
    public static final int PASSENGER_CANCELLED_TRIP_PUSH_ID = 103;
    public static final int PASSENGER_END_TRIP_PUSH_ID = 104;
    public static final int CURRENT_TRIP_PUSH_ID = 105;
    public static final int ADVANCE_TRIP_PUSH_ID = 106;
    public static final int PASSENGER_NOTIFY_DRIVER_ACCEPT_TRIP_PUSH_ID = 107;
    public static final int DRIVER_REACH_PASSENGER_LOCATION_PUSH_ID = 108;
    public static final int PASSENGER_NOT_PAY_WITH_CABHOUND_PUSH_ID = 110;
    public static final int DRIVER_SENDING_BILL_PUSH_ID = 111;
    public static final int PASSENGER_REQUESTING_FOR_DRIVER_PUSH_ID = 112;
    public static final int SENDING_BOOKING_NOTE_TO_DRIVER_PUSH_ID = 113;
    public static final int SAVE_TRIP_NOTE_TO_DRIVER_PUSH_ID = 114;
    public static final int DRIVER_CANCEL_ADVANCED_RESERVATION_PUSH_ID = 115;
    public static final int DRIVER_UPDATE_LOCATION_PUSH_MESSAGE_PUSH_ID = 116;
    public static final int DISPATCHER_SENDING_MESSAGE_PUSH_ID = 117;
    public static final int DISPATCHER_SENDING_TAG_MESSAGE_PUSH_ID = 118;
    public static final int PASSENGER_NOTIFY_DRIVER_EN_ROUTE_PUSH_ID = 119;
    public static final int RESENDING_TRIP_REQUEST_TO_DRIVERS_PUSH_ID = 120;
    public static final int TRIP_REQUEST_NOT_ACCEPTED_PUSH_ID = 121;
    public static final int PASSENGER_UPDATE_LOCATION_PUSH_MESSAGE_PUSH_ID = 122;
    public static final int DISPATCHER_BROADCAST_MESSAGE = 123;
    public static final int PASSENGER_NOTIFY_DRIVER_ACCEPT_ADVANCE_TRIP_PUSH_ID = 124;
    public static final int DISPATCHER_CLOSE_MESSAGE_PUSH_ID = 125;
    public static final int FUTURE_RESERVATION_NOT_ACCEPTED_ID = 126;
    public static final int PASSENGER_CANCEL_FUTURE_TRIP = 127;
    // for android gcm method
    public static final int ANDROID_GCM_RETRY_COUNT=5;
     
    public static final String APPEND_SECONDS= ":00";
    
    public static enum Reservation_Type
    {
    	Current,Advance;
    }
    public static String closed_trip_status="Cancelled Before Accepting";
    
    public static final int TRIP_EXPIRE_TIME = 8;
    public static final int ADVANCED_SCHEDULED_TRIP_EXPIRE_TIME = 60;
    
    public static final int ID_FOR_SCHEDULER = 0;
    public static final String CLOSE_TAG_MESSAGE = "close";
    public static final int RATING_IF_TRIP_LESS_10 = 5;
    public static final String MAIL_HELP_LINK="If you have questions, we have the answers.<br/>Contact us at ";
    public static final String UNAUTHORIZED = "Invalid credentials. Please try again.";
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
