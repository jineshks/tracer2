/**
 * 
 */
package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import play.Logger;

/**
 * @author Manzarul.Haque
 *
 */
public class DateUtil {
	
	private static DateUtil dateUtil = null;
	static{
		dateUtil = new DateUtil();
	}

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
     * this method take date object and time zone ,  time zone is optional
     * if time zone is not present then convert with default time zone
     * @param date     date object
     * @param timeZone
     * @return formatted date as String in "yyyy-MM-dd HH:mm:ss"
     */
    public static String getFormattedDateWithTimeZone(Date date, String... timeZone) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        if (timeZone != null && timeZone.length > 0) {
            format.setTimeZone(TimeZone.getTimeZone(timeZone[0]));
        } else {
            format.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));
        }
        String dateStr = format.format(date);
        Logger.debug("formatted timeStamp:" + dateStr);
        return dateStr;
    }
    
    /**
     * this method will format current date object
     * with incoming formatter
     * @param format  date formatter
     * @return  String formatted date object.
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

    /**
     * this method will format long time value to
     * given time zone with  MM-dd-yyyy HH:mm:ss
     * @param timeZone
     * @param time
     * @return
     */
    public static String convertLongToStringAsDateTime(String timeZone,long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date date = new Date(time);
        return format.format(date);
    }


    /**
     * this method will format long time value to
     * given time zone with  MM-dd-yyyy HH:mm:ss
     * @param timeZone
     * @param date
     * @param incomingDateFormat
     * @return
     */
    public static String dateFormatter(String timeZone,String date,String incomingDateFormat) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        SimpleDateFormat incomingFormatter = new SimpleDateFormat(incomingDateFormat);
        incomingFormatter.setTimeZone(TimeZone.getDefault());
        format.setTimeZone(TimeZone.getTimeZone(timeZone));
        String response = "";
        try {
        Date defaultFormattedDate = incomingFormatter.parse(date);
        response = format.format(defaultFormattedDate);
        }catch (Exception e){
            Logger.error(""+e);
        }
        return response;
    }    //


    /**
     * this method will format long time value to
     * given time zone with  MMMMM d EEEEEEEEE,hh:mm a
     * @param timeZone
     * @param date
     * @param incomingDateFormat
     * @return
     */
    public static String ormatterInMMMMdEEE(String timeZone,String date,String incomingDateFormat) {
        SimpleDateFormat format = new SimpleDateFormat("EEEEEEEEE, MMMMM d @hh:mm a");
        SimpleDateFormat incomingFormatter = new SimpleDateFormat(incomingDateFormat);
        incomingFormatter.setTimeZone(TimeZone.getDefault());
        format.setTimeZone(TimeZone.getTimeZone(timeZone));
        String response = "";
        try {
            Date defaultFormattedDate = incomingFormatter.parse(date);
            response = format.format(defaultFormattedDate);
        }catch (Exception e){
            Logger.error(""+e);
        }
        return response;
    }


    /**
     * this method will format long time value to
     * given time zone with  MMMMM d EEEEEEEEE,hh:mm a
     * @param timeZone
     * @param time
     * @return
     */
    public static String convertLongToStringAsMMMMEEEE(String timeZone,long time) {
        SimpleDateFormat format = new SimpleDateFormat("EEEEEEEEE, MMMMM d @hh:mm a");
        format.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date date = new Date(time);
        return format.format(date);
    }

    public static String getSqlTimeStamp(Date date,String ... timeZone) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(timeZone != null && timeZone.length>0){
            format.setTimeZone(TimeZone.getTimeZone(timeZone[0]));
        }
        String dateStr = format.format(date);
        Logger.debug("formatted timeStamp:" + dateStr);
        return dateStr;
    }

    /**
     * this method will convert long value to date 
     * object and provide formatted date object in 
     * "yyyy-MM-dd HH:mm:ss" this form
     * @param time  
     * @return
     */
    public static String getSqlTimeStamp(Long time) {
        return getSqlTimeStamp(new Date(time));
    }

    /**
     * @param date
     * @param timeZoneId
     * @return
     */
    public static Map<String, String> getFormattedDate(Date date,
                                                       String timeZoneId) {
        // final   long convertvalue =1000*3600*24;
        final DateFormat dayFormatter = new SimpleDateFormat("EEEE");
        final DateFormat dayOfYear = new SimpleDateFormat("D");
        final DateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
        Logger.debug("approve date and Currdate    is====" + date + "  " + new Date());
        TimeZone timezone = TimeZone.getTimeZone(timeZoneId);
        dayOfYear.setTimeZone(timezone);
        int currDay = Integer.parseInt(dayOfYear.format(new Date().getTime()));
        int futureDay = Integer.parseInt(dayOfYear.format(date.getTime()));
        Logger.debug("currdate  and  approve date==>" + currDay + " " + futureDay);
        Map<String, String> map = new HashMap<String, String>();
        int diff = futureDay - currDay;
        timeFormatter.setTimeZone(timezone);
        dayFormatter.setTimeZone(timezone);
        String day = dayFormatter.format(date.getTime());
        map.put("day", day);
        map.put("time", timeFormatter.format(date.getTime()));
        map.put("diff", diff + "");
        Logger.debug("day  time   diff" + day + +diff);
        return map;
    }

    /**
     *
     * @param date
     * @param timeZone
     * @return
     * @throws Exception
     */
    public static Date getDateInDefaultTimeZone (String date, String timeZone) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        SimpleDateFormat formatterWithDefaultTimeZone = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date reservationTimeWithTimeZone = format.parse(date+":00");
        String reservationTimeWithDefaultTimeZone  = formatterWithDefaultTimeZone.format(reservationTimeWithTimeZone);
        return formatterWithDefaultTimeZone.parse(reservationTimeWithDefaultTimeZone);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static Date getCurrentDate() throws Exception{
        SimpleDateFormat formatterWithDefaultTimeZone = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        Date currDate = new Date();
        String currTimeWithTimeZone = formatterWithDefaultTimeZone.format(currDate);
        return  formatterWithDefaultTimeZone.parse(currTimeWithTimeZone);
    }

    /**
     *
     * @param reservationTime
     * @return
     */
    public static boolean checkCurrentReservation(String reservationTime) {
        int timeDiff = 0;
        boolean hasReservation = false;
        try {
            Long reservationTimeInMili = convertStringToDateWithTime(reservationTime).getTime();
            Long currentTime = convertStringToDateWithTime(convertDateWithTimeZone(new Date(), Constants.TIME_ZONE)).getTime();
            timeDiff = (int) (reservationTimeInMili - currentTime) / (1000 * 60);
            if(timeDiff > 0 && timeDiff <=30){
              hasReservation = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasReservation;
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date convertStringToDateWithTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));
        Date afterFormat = null;
        try {
            afterFormat = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return afterFormat;
    }

    /**
     *
     * @return
     */
    public static Date getCurrentDateInGmt() {
        Calendar c = Calendar.getInstance();
        TimeZone z = c.getTimeZone();
        int offset = z.getRawOffset();
        if (z.inDaylightTime(new Date())) {
            offset = offset + z.getDSTSavings();
        }
        int offsetHrs = offset / 1000 / 60 / 60;
        int offsetMints = offset / 1000 / 60 % 60;
        c.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
        c.add(Calendar.MINUTE, (-offsetMints));
        return c.getTime();
    }

    /**
     * this method is used to take system current time and return time with time zone.
     *
     * @param date
     *            current date
     * @param timezone
     *            time
     * @return String 
     */
    public static String convertDateWithTimeZone(Date date, String timezone) {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormatGmt.format(date);

    }
   
 /**
  * this method is used to check reservation date and time should not be
  * before current date time.
  * @param time
  * @param timeZone
  * @return
  * @throws Exception
  */
    public static boolean isReservationTimeValid(String time,String timeZone)  throws Exception{
    	boolean  isTimeValid= true;
    	SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));
    	Date  reservationDate =  dateFormat.parse(time);
    	TracerLogger.debug("reservation date time with incoming time zone====>" + reservationDate, dateUtil);
    	//dateFormat.setTimeZone(TimeZone.getDefault());
    	reservationDate = dateFormat.parse(dateFormat.format(reservationDate));
    	TracerLogger.debug("reservation date time with default time zone====>" + reservationDate, dateUtil);
    	Date currentDate = DateUtil.convertStringToDateWithTime(DateUtil.getFormattedDateWithTimeZone(new Date()));
    	 if(reservationDate.before(currentDate)){ 
    		 isTimeValid = false;
         TracerLogger.debug("reservation time is not valid===>"+ isTimeValid, dateUtil);		 
    	 }
    	return isTimeValid;
    }
    
  public static void main(String[] args) {
	  getConvertDateForSmS("Asia/Calcutta","2013-09-27 11:22");
}  
 
  public static Date convertStringToDate(String date) {
	  SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
      Date afterFormat = null;
      try {
          afterFormat = format.parse(date);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return afterFormat;
  }
  
  public static String getConvertDateForSmS(String timeZone,String date) {
      SimpleDateFormat format = new SimpleDateFormat("dd MMM hh:mm a");
      SimpleDateFormat incomingFormatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
      incomingFormatter.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));
      format.setTimeZone(TimeZone.getTimeZone(timeZone));
      String response = "";
      try {
      Date defaultFormattedDate = incomingFormatter.parse(date+":00");
      response = format.format(defaultFormattedDate);
      }catch (Exception e){
          Logger.error(""+e);
      }
      return response;
  }    //
  
  public static final SimpleDateFormat dateFormatWithOutSeconds = new SimpleDateFormat("MM-dd-yyyy  HH:mm");
  public static final SimpleDateFormat dateFormatWithOutMilliSeconds = new SimpleDateFormat("MM-dd-yyyy  HH:mm:ss");
  
  
  /**
	 * get trip duration from milliseconds 
	 * @param tripDuration
	 * @return trip duration in String
	 */
	
	public static String getTripDurationFromMilliSeconds(long tripDuration)
	{

 	 String tripDurationString="";
 	   if(tripDuration>0)
 	   {
 		   if(tripDuration>=3600000) // 1hour=3600000 milliseconds
 		   {
 			   tripDurationString+=tripDuration/3600000+"h ";
 			   tripDuration=tripDuration%3600000;
 			   if(tripDuration>0)
 			   {
 				   tripDurationString+=tripDuration/60000+"m ";  
 				   tripDuration=tripDuration%60000;
 				   if(tripDuration>0)
     			   {
 					   tripDurationString+=tripDuration/1000+"s ";  
     			   }
 				   
 			   }
 			   
 		   }
 		   else
 		   {
 			   tripDurationString+=tripDuration/60000+"m ";      
 			   tripDuration=tripDuration%60000;
 				   if(tripDuration>0)
     			   {
 					   tripDurationString+=tripDuration/1000+"s ";  
     			   }
 				                  			   
 		   }
 		   
 	   }
 	   return tripDurationString;
	}
	
	
}
