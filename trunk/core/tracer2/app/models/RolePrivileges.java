/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.ebean.Model;

/*
 * @author Manzarul.Haque
 *
 */
@Entity
@Table(name="role_privilege")
public class RolePrivileges  extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	@OneToOne
	private Role role;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Privileges> privileges ;

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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the privileges
	 */
	public List<Privileges> getPrivileges() {
		return privileges;
	}

	/**
	 * @param privileges the privileges to set
	 */
	public void setPrivileges(List<Privileges> privileges) {
		this.privileges = privileges;
	}
 	
}
