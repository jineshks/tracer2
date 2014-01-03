/**
 * 
 */
package dataAccess.dao;

import java.util.List;
import java.util.Map;

import models.Ticket;
import models.User;

/**
 * @author Manzarul.Haque
 * 
 */
public interface TicketDao {
	
	/**
	 * this method is for creating new ticket.
	 * 
	 * @param ticket
	 *            Ticket object
	 * @param user
	 *            User object
	 * @return
	 */
	public boolean createTicket(Ticket ticket, User user) ;

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
	public boolean updateticket(String description, String ticketStatus, long ticketId, User user) ;
	/**
	 * this method will provide all ticket , assign to that user.
	 * 
	 * @param userId
	 *            long user id.
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicket(long userId) ;

	/**
	 * this method will provide all ticket based on project assign to that user.
	 * 
	 * @param userId
	 *            long user id.
	 * @param projectId
	 *            project id.
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByProject(long userId, long projectId) ; 

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
	public List<Ticket> getAllTicketByStatus(long userId, String status);

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
	public List<Ticket> getAllTicketByProjectAndStatus(long userId, String status, long projectId);

	/**
	 * this method will provide all ticket based on mile stone assign to that
	 * user.
	 * 
	 * @param userId
	 *            long user id.
	 * @param mileStoneId
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByMileStone(long userId, long mileStoneId) ;

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
	public List<Ticket> getAllTicketByMileStoneAndStatus(long userId, long mileStoneId, String status) ;
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
	public boolean updateMileStone(String status, long mileStoneId, String name) ;

	/**
	 * this method will provide all ticket based on mile stone and project .
	 * 
	 * @param projectId
	 *            int .
	 * @param mileStoneId
	 *            long
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByProjectAndMileStone(int projectId, long mileStoneId) ;
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
	public List<Ticket> getAllTicketByProjectAndMileStoneAndStatus(int projectId, long mileStoneId, String status) ;
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
	public List<Ticket> getTicketByMidAndStatusAndType(long mileStone, String status, int type);

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
	public List<Ticket> getTicketByStatusAndType(String status, int type);

	/**
	 * this method will provide ticket based on ticket type type may be
	 * {defect,enhancement , etc}
	 * 
	 * @param type
	 *            int type {1:defect,2:enhancement,3:testing etc}
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByType(int type) ;
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
	public List<Ticket> getTicketByProjectAndType(int projectId, int type) ;

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
	public List<Ticket> getTicketByProjectAndMidAndType(int projectId, long mileStoneId, int type) ;

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
	        int type) ;

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
	public List<Ticket> getTicketByProjectAndStatusAndType(int projectId, String status, int type) ;

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
	public List<Ticket> getTicketByMidAndType(long mileStoneId, int type);
	
	/**
	 * this method will provide all phase 
	 * details based on project id.
	 * @param projectId
	 * @return Map 
	 */
	public Map<Integer, String> getPhaseByProject(int projectId) ;
	
	/**
	 * This method is used to move ticket from one
	 * phase to another phase or one mile stone to another 
	 * mileStone. 
	 * @param ticketId long
	 * @param phaseId  long
	 * @param mileStoneId long
	 * @return  boolean
	 */
	public boolean moveTicket(long ticketId,long phaseId,long mileStoneId);
}
