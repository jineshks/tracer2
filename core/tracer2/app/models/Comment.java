/**
 * 
 */
package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.format.Formats;
import play.db.ebean.Model;

/**
 * @author Manzarul.Haque
 *
 */
@Entity(name="comments")
public class Comment extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	public int id;
	
	@Column(name="comment_text")
	public String text;
	
	@Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date created;
	
	@Column(name="user_id")
	public User user;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
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
