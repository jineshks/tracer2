/**
 * 
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

/**
 * @author Manzarul.Haque
 *
 */
@Entity
public class Phase  extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6432390862968562900L;

	@Id
    public Long id;
	
	@Column(name="phase_name")
	public String phaseName;
   
	   public static Model.Finder<Long,Phase> find = new Model.Finder<Long,Phase>(Long.class, Phase.class);

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
	 * @return the phaseName
	 */
	public String getPhaseName() {
		return phaseName;
	}

	/**
	 * @param phaseName the phaseName to set
	 */
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
}
