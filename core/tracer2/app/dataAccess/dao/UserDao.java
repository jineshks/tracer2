/**
 * 
 */
package dataAccess.dao;

import java.util.List;

import models.Project;
import models.User;
import responseBean.LoginResponseData;
import responseBean.MasterDataBean;

/**
 * @author Manzarul.Haque
 * 
 */
public  interface UserDao{

	/**
	 * this method will check user is valid or not. if user is valid then it
	 * will provide all project of that user.
	 * 
	 * @param userName
	 *            String user name
	 * @param password
	 *            String password
	 * @return LoginResponseData
	 */
	public LoginResponseData login(String userName, String password) ;

	/**
	 * this method will save user project.
	 * 
	 * @param projectName
	 *            name of the project
	 * @param description
	 *            project description
	 * @param visibility
	 *            int (1-public,2-private,3-org level)
	 * @param userId
	 *            long user id
	 * @return true/false
	 */
	public boolean addProject(String projectName, String description, int visibility, long userId);

	/**
	 * this method is used to assign new user to a project.
	 * 
	 * @param userId
	 * @param projectId
	 * @return
	 */
	public boolean addUserToProject(long userId, long projectId) ;

	/**
	 * this method is for inviting user to join tracer.
	 * 
	 * @param email
	 *            user email
	 * @return true/false
	 */
	public boolean inviteUser(final String email);

	/**
	 * this method will crate new mile stone.
	 * 
	 * @param name
	 *            mile stone name
	 * @param status
	 *            mile stone status
	 * @param endDate
	 *            mile stone end date
	 * @param project
	 *            project details for mile stone
	 * @return boolean
	 */
	public boolean createMileStone(String name, String status, String endDate, Project project);
	
	/**
	 * this method will provide master data means 
	 * current mile stone, all Severity Label,Complexity
	 * type ,State etc.
	 * @param projectId
	 * @return
	 */
	public MasterDataBean getMasterData(long projectId) ;
	
	/**
	 * this method will provide list of users.
	 * @param userId
	 * @return
	 */
	public List<User> getallUser(long userId) ;
	
	/**
	 * this method will check any mile stone with this project 
	 * is active or not.
	 * @param projectId
	 * @return
	 */
	public boolean isMileStoneActive(long projectId) ;
	
	/**
	 * This method is for user registration.
	 * @param user User 
	 * @return boolean
	 */
	public boolean registration(User user);
	
	/**
	 * This method will verify user incoming name is valid or 
	 * not. if valid then it will check that user is in our data base or not.
	 * @param username String user name
	 * @return   int
	 */
	public User verifyLink(String username);
}
