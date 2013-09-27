/**
 * 
 */
package util;

/**
 * this class overrides play loggers. in play logger do not provide class name
 * and line number.
 * 
 * @author Manzarul.Haque
 * 
 */
public class TracerLogger {
	
	/**
	 * this method provides message along with class name and line number
	 * 
	 * @param data
	 *            String message
	 * @param object
	 *            calling class object
	 */
	public static void debug(String data, Object object) {
		play.Logger.debug(data + getClassNameAndLineNo(object));
	}

	/**
	 * this method provides message along with class name and line number
	 * 
	 * @param data
	 *            String message
	 * @param object
	 *            calling class object
	 */
	public static void error(String data, Object object) {
		play.Logger.error(data + getClassNameAndLineNo(object));
	}

	/**
	 * this method provides message along with class name and line number
	 * 
	 * @param data
	 *            String message
	 * @param object
	 *            calling class object
	 */
	public static void info(String data, Object object) {
		play.Logger.error(data + getClassNameAndLineNo(object));
	}

	/**
	 * this method is used to print class name and line number
	 * 
	 * @param object
	 * @return
	 */
	private static String getClassNameAndLineNo(Object object) {
		String message = "";
		if(object == null) {
			return message ="can't get line number.";
		}
		message = " " + object.getClass().getName() + "********"
				+ Thread.currentThread().getStackTrace()[3].getLineNumber();
		return message;
	}


}
