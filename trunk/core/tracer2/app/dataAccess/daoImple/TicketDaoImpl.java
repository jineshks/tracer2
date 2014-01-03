/**
 * 
 */
package dataAccess.daoImple;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Comment;
import models.Complexity;
import models.MileStone;
import models.Phase;
import models.Project;
import models.Severity;
import models.Ticket;
import models.User;
import util.TrackLogger;


import dataAccess.dao.TicketDao;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;


/**
 * @author Manzarul.Haque
 * 
 */
public class TicketDaoImpl  implements  TicketDao{
	
	private static final String className = TicketDaoImpl.class.getName();
   
	/*
     * getPhaseSql   
     */
	private String getPhaseSql = "select DISTINCT ph.id,ph.phase_name from phase ph,ticket t  where t.project_id = :projectId  and t.phase_id=ph.id ";
	/**
	 * this method is for creating new ticket.
	 * 
	 * @param ticket
	 *            Ticket object
	 * @param user
	 *            User object
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
	 * 
	 * @param description
	 *            String description.
	 * @param ticketStatus
	 *            String ticket status
	 * @param ticketId
	 *            long ticket id.
	 * @param user
	 *            User object.
	 * @return true/false
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
	 * 
	 * @param userId
	 *            long user id.
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicket(long userId) {
		return Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).order().desc("phase_id").findList();
	}

	/**
	 * this method will provide all ticket based on project assign to that user.
	 * 
	 * @param userId
	 *            long user id.
	 * @param projectId
	 *            project id.
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByProject(long userId, long projectId) {
		Expression checkForCreater = Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId));
		return Ebean.createQuery(Ticket.class).where().eq("project_id", projectId).add(checkForCreater).order().desc("phase_id").findList();
	}

	/**
	 * this method will provide all ticket based on project,status assign to
	 * that user.
	 * 
	 * @param userId
	 *            long user id.
	 * @param status
	 *            ticket status (open, closed, active, pending)
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByStatus(long userId, String status) {
		Expression checkForCreater = Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId));
		return Ebean.createQuery(Ticket.class).where().eq("ticket_status", status).add(checkForCreater).order().desc("phase_id").findList();
	}

	/**
	 * this method will provide all ticket based on project,status assign to
	 * that user.
	 * 
	 * @param userId
	 *            long user id.
	 * @param status
	 *            ticket status (open, closed, active, pending)
	 * @param projectId
	 *            long project id
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByProjectAndStatus(long userId, String status, long projectId) {
		Expression checkForCreater = Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId));
		return Ebean.createQuery(Ticket.class).where().eq("ticket_status", status).eq("project_id", projectId)
		        .add(checkForCreater).order().desc("phase_id").findList();
	}

	/**
	 * this method will provide all ticket based on mile stone assign to that
	 * user.
	 * 
	 * @param userId
	 *            long user id.
	 * @param mileStoneId
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByMileStone(long userId, long mileStoneId) {
		Expression checkForCreater = Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId));
		return Ebean.createQuery(Ticket.class).where().eq("mile_stone_id", mileStoneId).add(checkForCreater).order().desc("phase_id").findList();
	}

	/**
	 * this method will provide all ticket based on mile stone and milestone
	 * status assign to that user.
	 * 
	 * @param userId
	 *            long user id.
	 * @param mileStoneId
	 * @param status
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByMileStoneAndStatus(long userId, long mileStoneId, String status) {
		Expression checkForCreater = Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId));
		return Ebean.createQuery(Ticket.class).fetch("mileStone").where().eq("mile_stone_id", mileStoneId)
		        .eq("mile_stone_status", status).add(checkForCreater).order().desc("phase_id").findList();
	}

	/**
	 * this method is used to update the mile stone status.
	 * 
	 * @param status
	 *            String (active,pending,backlog etc)
	 * @param mileStoneId
	 *            long
	 * @param name
	 *            name of milestone
	 * @return boolean
	 */
	public boolean updateMileStone(String status, long mileStoneId, String name) {
		boolean response = true;
		try {
			MileStone mileStone = Ebean.createQuery(MileStone.class).where().eq("id", mileStoneId).findUnique();
			mileStone.setStatus(status);
			mileStone.setName(name);
			Ebean.update(mileStone);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		}
		return response;
	}

	/**
	 * this method will provide all ticket based on mile stone and project .
	 * 
	 * @param projectId
	 *            int .
	 * @param mileStoneId
	 *            long
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByProjectAndMileStone(int projectId, long mileStoneId) {
		return Ebean.createQuery(Ticket.class).fetch("mileStone").where().eq("mile_stone_id", mileStoneId)
		        .eq("id", projectId).findList();
	}

	/**
	 * this method will provide all ticket based on mile stone,project and
	 * ticket status.
	 * 
	 * @param projectId
	 *            int .
	 * @param mileStoneId
	 *            long
	 * @param status
	 *            String
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByProjectAndMileStoneAndStatus(int projectId, long mileStoneId, String status) {
		return Ebean.createQuery(Ticket.class).fetch("mileStone").where().eq("mile_stone_id", mileStoneId)
		        .eq("id", projectId).eq("ticket_status", status).findList();
	}

	/**
	 * this method will provide ticket based on mile stone , ticket status
	 * {active,pending,close etc} and ticket type type may be
	 * {defect,enhancement , etc}
	 * 
	 * @param mileStone
	 *            long mile stone id
	 * @param status
	 *            String status
	 * @param type
	 *            int type {1:defect,2:enhancement,3:testing etc}
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByMidAndStatusAndType(long mileStone, String status, int type) {
		return Ebean.createQuery(Ticket.class).where().eq("mile_stone_id", mileStone)
		        .eq("type_id", type).eq("ticket_status", status).findList();
	}

	/**
	 * this method will provide ticket based on ticket status
	 * {active,pending,close etc} and ticket type type may be
	 * {defect,enhancement , etc}
	 * 
	 * @param status
	 *            String status
	 * @param type
	 *            int type {1:defect,2:enhancement,3:testing etc}
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByStatusAndType(String status, int type) {
		return Ebean.createQuery(Ticket.class).where().eq("type_id", type)
		        .eq("ticket_status", status).findList();
	}

	/**
	 * this method will provide ticket based on ticket type type may be
	 * {defect,enhancement , etc}
	 * 
	 * @param type
	 *            int type {1:defect,2:enhancement,3:testing etc}
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByType(int type) {
		return Ebean.createQuery(Ticket.class).where().eq("type_id", type).findList();
	}

	/**
	 * this method will provide ticket based on project and ticket type type may
	 * be {defect,enhancement , etc}
	 * 
	 * @param projectId
	 *            int
	 * @param type
	 *            int type {1:defect,2:enhancement,3:testing etc}
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByProjectAndType(int projectId, int type) {
		return Ebean.createQuery(Ticket.class).where().eq("type_id", type)
		        .eq("project_id", projectId).findList();
	}

	/**
	 * this method will provide ticket based on project and mileStone and ticket
	 * type type may be {defect,enhancement , etc}
	 * 
	 * @param projectId
	 *            int
	 * @param mileStoneId
	 *            long
	 * @param type
	 *            int type {1:defect,2:enhancement,3:testing etc}
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByProjectAndMidAndType(int projectId, long mileStoneId, int type) {
		return Ebean.createQuery(Ticket.class).where().eq("mile_stone_id", mileStoneId)
		        .eq("type_id", type).findList();
	}

	/**
	 * this method will provide ticket based on project and mileStone and status
	 * and ticket type type may be {defect,enhancement , etc}
	 * 
	 * @param projectId
	 *            int
	 * @param mileStoneId
	 *            long
	 * @param status
	 * @param type
	 *            int type {1:defect,2:enhancement,3:testing etc}
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByProjectAndMidAndStatusAndType(int projectId, long mileStoneId, String status,
	        int type) {
		return Ebean.createQuery(Ticket.class).where().eq("mile_stone_id", mileStoneId)
		        .eq("type_id", type).eq("ticket_status", status).eq("project_id", projectId).findList();
	}

	/**
	 * this method will provide ticket based on project and status and ticket
	 * type type may be {defect,enhancement , etc}
	 * 
	 * @param projectId
	 *            int
	 * @param status
	 * @param type
	 *            int type {1:defect,2:enhancement,3:testing etc}
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByProjectAndStatusAndType(int projectId, String status, int type) {
		return Ebean.createQuery(Ticket.class).where().eq("type_id", type)
		        .eq("ticket_status", status).eq("project_id", projectId).findList();
	}

	/**
	 * this method will provide ticket based on project and status and ticket
	 * type type may be {defect,enhancement , etc}
	 * 
	 * @param mileStoneId
	 *            long
	 * @param type
	 *            int type {1:defect,2:enhancement,3:testing etc}
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByMidAndType(long mileStoneId, int type) {
		return Ebean.createQuery(Ticket.class).where().eq("mile_stone_id", mileStoneId)
		        .eq("type_id", type).findList();
	}
	
	
	/**
	 * this method will provide all phase 
	 * details based on project id.
	 * @param projectId
	 * @return Map 
	 */
	public Map<Integer, String> getPhaseByProject(int projectId) {
		SqlQuery sqlQuery = Ebean.createSqlQuery(getPhaseSql);
		Map<Integer, String> phaseMap = new HashMap<Integer, String>();
		sqlQuery.setParameter("projectId", projectId);
		List<SqlRow> list = sqlQuery.findList();
		for (int i = 0; i < list.size(); i++) {
			SqlRow row = list.get(i);
			phaseMap.put(row.getInteger("id"), row.getString("phase_name"));
		}
		return phaseMap;
	}
	
	
	/**
	 * This method is used to move ticket from one
	 * phase to another phase or one mile stone to another 
	 * mileStone. 
	 * @param ticketId long
	 * @param phaseId  long
	 * @param mileStoneId long
	 * @return  boolean
	 */
	public boolean moveTicket(long ticketId, long phaseId, long mileStoneId) {
		boolean response = true;
		try {
			Ticket ticket = Ebean.createQuery(Ticket.class).where().eq("id", ticketId).findUnique();
			Phase phase = new Phase();
			phase.setId(phaseId);
			MileStone mileStone = new MileStone();
			mileStone.setId(mileStoneId);
			ticket.setPhase(phase);
			ticket.setMileStone(mileStone);
			Ebean.update(ticket);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		}
		return response;
	}
}
