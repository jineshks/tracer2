/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.data.format.Formats.DateTime;
import play.db.ebean.Model;
import util.DateUtil;

/**
 * @author Manzarul.Haque
 * 
 */
@Entity
public class Project extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Column(name = "project_name")
	private String name;

	private String description;

	@Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;

	@DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;
	@ManyToOne
	private Visibility visibility;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private List<User> user;
	
	@Column(name="creater")
	@ManyToOne(cascade=CascadeType.ALL)
	private  User createdBy;
	public static Model.Finder<Long,Project> find = new Model.Finder<Long,Project>(Long.class, Project.class);
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return  DateUtil.getFormattedDateWithTimeZone(created);
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return  DateUtil.getFormattedDateWithTimeZone(updated);
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	/**
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return visibility;
	}
	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	/**
	 * @return the user
	 */
	public List<User> getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(List<User> user) {
		this.user = user;
	}
	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
}
