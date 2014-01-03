/**
 * 
 */
package services.serviceImpl;

import java.util.List;

import models.Project;
import models.User;
import dataAccess.dao.UserDao;
import dataAccess.daoFactory.DaoFactory;
import dataAccess.daoImple.UserDaoImpl;
import responseBean.LoginResponseData;
import responseBean.MasterDataBean;
import services.service.UserService;
import util.Constants;
import util.TrackLogger;

/**
 * @author Manzarul.Haque
 *
 */
public class UserServiceImpl  implements UserService{
	private static final String className = UserServiceImpl.class.getName();

	/**
	 * this method will check user is valid or not.
	 * if user is valid then it will provide all project 
	 * of that user.
	 * @param userName String user name
	 * @param password  String password
	 * @return LoginResponseData
	 */
	public LoginResponseData login(String userName, String password) { 
		UserDao dao  = (UserDaoImpl)DaoFactory.getInstance(Constants.USER_DAO);
		return dao.login(userName, password);
	}
	
	/**
	 * This method will add a new project.
	 * @param projectName String 
	 * @param description String
	 * @param visibility int 
	 * @param userId long
	 * @return boolean
	 */
	public boolean addProject(String projectName, String description, int visibility, long userId) {
		UserDao userDao = (UserDaoImpl) DaoFactory.getInstance(Constants.USER_DAO);
		return userDao.addProject(projectName, description, visibility, userId);
	}
	
	/**
	 * This method is used to add new user to the project.
	 * @param userId long
	 * @param projectId long
	 * @return boolean
	 */
	public boolean addUserToProject(long userId, long projectId) {
		UserDao userDao = (UserDaoImpl) DaoFactory.getInstance(Constants.USER_DAO);
		return userDao.addUserToProject(userId, projectId);
	}
	
	/**
	 * This method is used to invite user to join 
	 * tracer.
	 * @param email String
	 * @return boolean
	 */
	public boolean inviteUser(String email){
		UserDao userDao = (UserDaoImpl) DaoFactory.getInstance(Constants.USER_DAO);
	     return userDao.inviteUser(email);
	}
	
	/**
	 * This method is for user registration.
	 * @param user User 
	 * @return boolean
	 */
	public boolean registration(User user) {
		UserDao userDao = (UserDaoImpl) DaoFactory.getInstance(Constants.USER_DAO);
		return userDao.registration(user);
	}

	/**
	 * This method is for creating new mile stone.
	 * @param name String 
	 * @param status  String
	 * @param endDate String
	 * @param project Project
	 * @return boolean
	 */
	public boolean createMileStone(String name, String status, String endDate , Project project){
		UserDao userDao = (UserDaoImpl) DaoFactory.getInstance(Constants.USER_DAO);
		  return userDao.createMileStone(name, status, endDate, project);
	}
	/**
	 * This method will provide all maseter data related 
	 * to project.
	 * @param projectId long
	 * @return MasterDataBean
	 */
	public  MasterDataBean getMasterData(long projectId) {
		UserDao userDao = (UserDaoImpl) DaoFactory.getInstance(Constants.USER_DAO);
		return userDao.getMasterData(projectId);
	}
	
	/**
	 * This method will provide all user details.
	 * @param userId long
	 * @return  List<User>
	 */
	public List<User>  getallUser(long userId) {
		UserDao userDao = (UserDaoImpl) DaoFactory.getInstance(Constants.USER_DAO);
		return userDao.getallUser(userId);
	}
}
