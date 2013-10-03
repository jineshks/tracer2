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
	
	private List<Project> projrctList;
   private 	Session  userInfo;
/**
 * @return the projrctList
 */
public List<Project> getProjrctList() {
	return projrctList;
}
/**
 * @param projrctList the projrctList to set
 */
public void setProjrctList(List<Project> projrctList) {
	this.projrctList = projrctList;
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
