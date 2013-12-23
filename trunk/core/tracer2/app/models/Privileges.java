/**
 * 
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * @author Manzarul.Haque
 *
 */
@Entity
@Table(name="privilege")
public class Privileges  extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	
	@Column(name="privilege_name")
	private String name;

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
	
	
}
