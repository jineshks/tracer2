/**
 * 
 */
package service;

import Dao.UserDao;
import responseBean.LoginResponseData;
import util.TrackLogger;

/**
 * @author Manzarul.Haque
 *
 */
public class UserService {
	private static final String className = UserService.class.getName();
	private static UserService service ;
	static {
		try {
	        service = new UserService();
        } catch (CloneNotSupportedException e) {
        	TrackLogger.error(e.getMessage(), className);
        }
	}
    /**
     * UserService default constructor.
     */
	private UserService () throws CloneNotSupportedException{
		if(service != null) 
			throw  new CloneNotSupportedException();
		
	}
	
	public UserService clone() throws CloneNotSupportedException {
		throw  new CloneNotSupportedException();
	}
	
	/**
	 * this method will provide UserService instance
	 * @return UserService instance
	 * @throws CloneNotSupportedException
	 */
	public  static UserService getInstance()  throws CloneNotSupportedException{
		if(service != null) {
			return service;
		}
		service = new UserService();
		return service;
	}
	
	/**
	 * this method will check user is valid or not.
	 * if user is valid then it will provide all project 
	 * of that user.
	 * @param userName String user name
	 * @param password  String password
	 * @return LoginResponseData
	 */
	public LoginResponseData login(String userName, String password) { 
		UserDao dao = UserDao.getInstance();
		return dao.login(userName, password);
	}
	
	
}
