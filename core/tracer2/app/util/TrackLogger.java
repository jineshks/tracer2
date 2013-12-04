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
public class TrackLogger {
	
	/**
	 * this method provides message along with class name and line number
	 * 
	 * @param data
	 *            String message
	 * @param className
	 *            calling class name
	 */
	public static void debug(String data, String className) {
		play.Logger.debug(data + getClassNameAndLineNo(className));
	}

	/**
	 * this method provides message along with class name and line number
	 * 
	 * @param data
	 *            String message
	 * @param className
	 *            calling class name
	 */
	public static void error(String data, String className) {
		play.Logger.error(data + getClassNameAndLineNo(className));
	}

	/**
	 * this method provides message along with class name and line number
	 * 
	 * @param data
	 *            String message
	 * @param className
	 *            calling class name
	 */
	public static void info(String data, String className) {
		play.Logger.error(data + getClassNameAndLineNo(className));
	}

	/**
	 * this method is used to print class name and line number
	 * 
	 * @param className
	 * @return
	 */
	private static String getClassNameAndLineNo(String className) {
		String message = "";
		if(className == null) {
			return message ="can't get line number.";
		}
		message =  className+ "********"
				+ Thread.currentThread().getStackTrace()[3].getLineNumber();
		return message;
	}


}
