package util;
/**
 * this is a util class used to read data from
 * property file.
 * @author Manzarul.Haque
 *
 */
public class PropertyReader {
    /**
     * this method will read property file data based 
     * on key.
     * @param key key of property file.
     * @return  String
     */
	public static String readProperty(String key) {
		return play.Play.application().configuration().getString(key);
	}

}
