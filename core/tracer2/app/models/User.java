/**
 * 
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * @author Manzarul.Haque
 *
 */
@Entity
public class User  extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	private String name;
	
	@Column(unique=true)
	private String email;
	
	@Constraints.Required
	private String phone;
	
	@Column(name="pwd")
	private String password;
  
	public static Model.Finder<Long,User> find = new Model.Finder<Long,User>(Long.class, User.class);

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		if(password.length()>4) {
		return "XXXX"+password.subSequence(password.length()-4 ,password.length());
		} 
		return "XXXXXXX";
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	} 
	
}
