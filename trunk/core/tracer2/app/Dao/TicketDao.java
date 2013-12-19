/**
 * 
 */
package Dao;

import java.util.Date;
import java.util.List;

import models.Comment;
import models.Complexity;
import models.MileStone;
import models.Phase;
import models.Project;
import models.Severity;
import models.Ticket;
import models.User;
import util.TrackLogger;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

/**
 * @author Manzarul.Haque
 *
 */
public enum TicketDao {
	instance;
	private static final String className = TicketDao.class.getName();
	 
	/**
	 * this method is for creating new ticket.
	 * @param ticket Ticket object
	 * @param user User object
	 * @return
	 */
	public boolean createTicket(Ticket ticket, User user) {
		boolean response = true;
		try {

			Project project = Ebean.createQuery(Project.class).where().eq("id", ticket.getProject().getId())
			        .findUnique();
			MileStone mileStone = Ebean.createQuery(MileStone.class).where().eq("id", ticket.getMileStone().getId())
			        .findUnique();
			User owner = Ebean.createQuery(User.class).where().eq("id", ticket.getOwner().getId()).findUnique();
			Severity severity = Ebean.createQuery(Severity.class).where().eq("id", ticket.getSeverity().getId())
			        .findUnique();
			Complexity complexity = Ebean.createQuery(Complexity.class).where()
			        .eq("id", ticket.getComplexity().getId()).findUnique();
			Phase phase = Ebean.createQuery(Phase.class).where().eq("id", ticket.getPhase().getId()).findUnique();
			ticket.setComplexity(complexity);
			ticket.setCreated(new Date());
			ticket.setCreater(user);
			ticket.setMileStone(mileStone);
			ticket.setOwner(owner);
			ticket.setPhase(phase);
			ticket.setProject(project);
			ticket.setSeverity(severity);
			ticket.setUpdated(new Date());
			Ebean.save(ticket);
			Comment comment = new Comment();
			comment.setCreated(new Date());
			comment.setText(ticket.getDescription());
			comment.setTicket(ticket);
			comment.setUser(user);
			Ebean.save(comment);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		}
		return response;
	}
	
	/**
	 * this method will update an existing ticket.
	 * @param description String description.
	 * @param ticketStatus String ticket status
	 * @param ticketId     long ticket id.
	 * @param user     	User object.
	 * @return            true/false
	 */
	public boolean updateticket(String description, String ticketStatus, long ticketId, User user) {
		boolean response = true;
		try {
			Ticket ticket = Ebean.createQuery(Ticket.class).where().eq("id", ticketId).findUnique();
			ticket.setTicketStatus(ticketStatus);
			ticket.setUpdated(new Date());
			Ebean.update(ticket);
			Comment comment = new Comment();
			comment.setCreated(new Date());
			comment.setText(description);
			comment.setTicket(ticket);
			comment.setUser(user);
			Ebean.save(comment);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		}
		return response;
	}
	
	/**
	 * this method will provide all ticket , assign to that user.
	 * @param userId long user id.
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicket(long userId) {
		return Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).findList();
	}
	
	

	/**
	 * this method will provide all ticket based on project assign to that user.
	 * @param userId long user id.
	 * @param projectId  project id.
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByProject(long userId,long projectId) {
		return  Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("project_id", projectId).findList();
	}
	
	/**
	 * this method will provide all ticket based on project,status  assign to that user.
	 * @param userId long user id.
	 * @param status  ticket status (open, closed, active, pending)
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByStatus(long userId, String status) {
		return Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("ticket_status", status).findList();
	}
	
	/**
	 * this method will provide all ticket based on project,status  assign to that user.
	 * @param userId long user id.
	 * @param status  ticket status (open, closed, active, pending)
	 * @param projectId long project id
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByProjectAndStatus(long userId, String status,long projectId) {
		return Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("ticket_status", status).eq("project_id", projectId).findList();
	}
	
	/**
	 * this method will provide all ticket based on mile stone  assign to that user.
	 * @param userId long user id.
	 * @param mileStoneId
      * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByMileStone(long userId, long mileStoneId) {
		return Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("mile_stone_id", mileStoneId).findList();
	}
	
	
	
	/**
	 * this method will provide all ticket based on mile stone and milestone status  assign to that user.
	 * @param userId long user id.
	 * @param mileStoneId
	 * @param status
      * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByMileStoneAndStatus(long userId, long mileStoneId, String status) {
		return Ebean.createQuery(Ticket.class).fetch("mileStone")
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("mile_stone_id", mileStoneId).eq("mile_stone_status", status).findList();
	}
	
	/**
	 * this method is used to update the mile stone status.
	 * @param status String (active,pending,backlog etc)
	 * @param mileStoneId  long
	 * @return  boolean
	 */
	public boolean updateMileStone(String status, long mileStoneId) {
		boolean response = true;
		try {
			MileStone mileStone = Ebean.createQuery(MileStone.class).where().eq("id", mileStoneId).findUnique();
			mileStone.setStatus(status);
			Ebean.update(mileStone);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		}
		return response;
	}
	
	/**
	 * this method will provide all ticket based on mile stone and project .
	 * @param projectId int .
	 * @param mileStoneId long
      * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByProjectAndMileStone(int projectId, long mileStoneId) {
		return Ebean.createQuery(Ticket.class).fetch("mileStone").where().eq("mile_stone_id", mileStoneId)
		        .eq("id", projectId).findList();
	}
   
	/**
	 * this method will provide all ticket based on mile stone,project and ticket status.
	 * @param projectId int .
	 * @param mileStoneId long
	 * @param status String
      * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByProjectAndMileStoneAndStatus(int projectId, long mileStoneId, String status) {
		return Ebean.createQuery(Ticket.class).fetch("mileStone").where().eq("mile_stone_id", mileStoneId)
		        .eq("id", projectId).eq("ticket_status", status).findList();
	}
	
	
}
