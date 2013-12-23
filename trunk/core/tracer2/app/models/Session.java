/**
 * 
 */
package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.format.Formats;
import play.data.format.Formats.DateTime;
import play.db.ebean.Model;
import util.DateUtil;

/**
 * @author Manzarul.Haque
 *
 */
@Entity
@Table(name="user_session")
public class Session  extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	@Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;

	@DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;
	
	private String sessionId;
	@ManyToOne
	private User user;
	 public static Model.Finder<Long,Session> find = new Model.Finder<Long,Session>(Long.class, Session.class);
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
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
	 
}
