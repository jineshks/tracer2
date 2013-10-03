/**
 * 
 */
package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * @author Manzarul.Haque
 *
 */
@Entity
@Table(name="user_project")
public class UserProject  extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	public long id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public User user ;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Project project;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
