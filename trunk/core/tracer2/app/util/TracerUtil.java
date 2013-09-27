/**
 * 
 */
package util;

import java.util.Random;

/**
 * @author Manzarul.Haque
 *
 */
public class TracerUtil {
	private static TracerUtil tracerUtil = null;
	static {
		tracerUtil = new TracerUtil();
	}
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
		TracerLogger.debug(builder.toString(),tracerUtil);
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(getUniqueId("manzarul07@gmail.com"));
	}

}
