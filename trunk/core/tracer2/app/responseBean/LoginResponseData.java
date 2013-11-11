/**
 * 
 */
package responseBean;

import java.util.List;

import models.Project;
import models.Session;

/**
 * @author Manzarul.Haque
 *
 */
public class LoginResponseData {
	
	private List<Project> projectList;
   private 	Session  userInfo;
/**
 * @return the projrctList
 */
public List<Project> getProjectList() {
	return projectList;
}
/**
 * @param projrctList the projrctList to set
 */
public void setProjectList(List<Project> projrctList) {
	this.projectList = projrctList;
}
/**
 * @return the userInfo
 */
public Session getUserInfo() {
	return userInfo;
}
/**
 * @param userInfo the userInfo to set
 */
public void setUserInfo(Session userInfo) {
	this.userInfo = userInfo;
} 

}
