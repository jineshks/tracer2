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
@Table(name="role")
public class Role  extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	
	@Column(name="role_name")
	public String name;
	
}
