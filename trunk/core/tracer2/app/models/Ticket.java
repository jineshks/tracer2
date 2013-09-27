/**
 * 
 */
package models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.format.Formats;
import play.data.format.Formats.DateTime;
import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * @author Manzarul.Haque
 *
 */
@Entity
public class Ticket  extends Model{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    public Long id;
    
    @Constraints.Required
    public String title;
    
    @Column
    public String description;
    
    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date created;
    
    @DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date updated;
    
    @ManyToOne
    public Phase phase;
    
    @Column(name="ticket_status")
    public String ticketStatus;
   
    @ManyToOne
    public User ownerId;
    
    @ManyToOne
    public User createrId;
    
    @ManyToOne
    public MileStone mileStone;
    
    @ManyToOne
    public Severity severity;
    
    @ManyToOne
    public Complexity complexity;
    
    @OneToMany
    public Project project;
    @OneToMany
    public Type type;
    public static Model.Finder<Long,Ticket> find = new Model.Finder<Long,Ticket>(Long.class, Ticket.class);
	/**
	 * @return the dateFormat
	 */
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	/**
	 * @param dateFormat the dateFormat to set
	 */
	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	/**
	 * @return the phase
	 */
	public Phase getPhase() {
		return phase;
	}
	/**
	 * @param phase the phase to set
	 */
	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	/**
	 * @return the ticketStatus
	 */
	public String getTicketStatus() {
		return ticketStatus;
	}
	/**
	 * @param ticketStatus the ticketStatus to set
	 */
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	/**
	 * @return the ownerId
	 */
	public User getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the createrId
	 */
	public User getCreaterId() {
		return createrId;
	}
	/**
	 * @param createrId the createrId to set
	 */
	public void setCreaterId(User createrId) {
		this.createrId = createrId;
	}
	/**
	 * @return the mileStone
	 */
	public MileStone getMileStone() {
		return mileStone;
	}
	/**
	 * @param mileStone the mileStone to set
	 */
	public void setMileStone(MileStone mileStone) {
		this.mileStone = mileStone;
	}
	/**
	 * @return the severity
	 */
	public Severity getSeverity() {
		return severity;
	}
	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	/**
	 * @return the complexity
	 */
	public Complexity getComplexity() {
		return complexity;
	}
	/**
	 * @param complexity the complexity to set
	 */
	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}
	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}
    
}
