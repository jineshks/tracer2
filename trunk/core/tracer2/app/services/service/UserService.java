/**
 * 
 */
package services.service;

import java.util.List;

import models.Project;
import models.User;
import responseBean.LoginResponseData;
import responseBean.MasterDataBean;

/**
 * @author Manzarul.Haque
 *
 */
public interface UserService {

	/**
	 * this method will check user is valid or not.
	 * if user is valid then it will provide all project 
	 * of that user.
	 * @param userName String user name
	 * @param password  String password
	 * @return LoginResponseData
	 */
	public LoginResponseData login(String userName, String password);
	/**
	 * This method will add a new project.
	 * @param projectName String 
	 * @param description String
	 * @param visibility int 
	 * @param userId long
	 * @return boolean
	 */
	public boolean addProject(String projectName,String description, int visibility, long userId);
	/**
	 * This method is used to add new user to the project.
	 * @param userId long
	 * @param projectId long
	 * @return boolean
	 */
	public boolean addUserToProject(long userId, long projectId);
	/**
	 * This method is used to invite user to join 
	 * tracer.
	 * @param email String
	 * @return boolean
	 */
	public boolean inviteUser(String email);
	/**
	 * This method is for user registration.
	 * @param user User 
	 * @return boolean
	 */
	public boolean registration(User user);
	/**
	 * This method is for creating new mile stone.
	 * @param name String 
	 * @param status  String
	 * @param endDate String
	 * @param project Project
	 * @return boolean
	 */
	public boolean createMileStone(String name, String status, String endDate , Project project);
	/**
	 * This method will provide all maseter data related 
	 * to project.
	 * @param projectId long
	 * @return MasterDataBean
	 */
	public  MasterDataBean getMasterData(long projectId);
	/**
	 * This method will provide all user details.
	 * @param userId long
	 * @return  List<User>
	 */
	public List<User>  getallUser(long userId);
	/**
	 * This method will verify user incoming name is valid or 
	 * not. if valid then it will check that user is in our data base or not.
	 * @param username String user name
	 * @return   User
	 */
	public User verifyLink(String username);
}
