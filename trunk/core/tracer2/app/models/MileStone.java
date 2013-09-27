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
@Entity
public class MileStone extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5982733991310792271L;
	@Id
	public long id;
	
	@Column(name="mile_stone_name")
	public String name;
	
	@Column(name="mile_stone_status")
	public String status;
	
	 @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
	    public Date created;
	    
	    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
	    public Date ended;
	    
	    public static Model.Finder<Long,MileStone> find = new Model.Finder<Long,MileStone>(Long.class, MileStone.class);

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
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
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
		 * @return the ended
		 */
		public Date getEnded() {
			return ended;
		}

		/**
		 * @param ended the ended to set
		 */
		public void setEnded(Date ended) {
			this.ended = ended;
		}
}
