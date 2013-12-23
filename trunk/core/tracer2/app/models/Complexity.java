/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

/**
 * @author Manzarul.Haque
 *
 */
@Entity
public class Complexity  extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String category;

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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
}
