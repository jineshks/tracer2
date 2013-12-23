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
@Table(name="types")
public class Type  extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6544484210624421757L;
	
	@Id
	private Long id;
	@Column(name="ticket_type")
	private String ticketType;
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
	 * @return the ticketType
	 */
	public String getTicketType() {
		return ticketType;
	}
	/**
	 * @param ticketType the ticketType to set
	 */
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	} 
}
