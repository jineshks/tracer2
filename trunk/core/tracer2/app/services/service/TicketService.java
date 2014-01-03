/**
 * 
 */
package services.service;

import java.util.List;
import java.util.Map;

import responseBean.TestCaseResponse;

import models.Ticket;
import models.User;

/**
 * @author Manzarul.Haque
 * this service class contains 
 * all ticket api related business logic
 * and after that it will call db.
 */
public interface TicketService {
   
	/**
	 * This method will provide list of ticket based on
	 * project mile stone and status. if status value is empty
	 * or null then we provide ticket details based on 
	 * remaining two parameters.
	 * @param projectId int
	 * @param mileStoneId long
	 * @param status String ('active','closed','pending','')
	 * @param typeId int
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByPidMileStoneAndStatus(int projectId, long mileStoneId, String status, int typeId) ;
	
	/**
	 * this method will provide all phase 
	 * details based on project id.
	 * @param projectId
	 * @return  Map
	 */
	public Map<Integer, String> getPhaseByProject(int projectId);
	/**
	 * This method is used to crate ticket.
	 * @param ticket Ticket object.
	 * @param user User object.
	 * @return boolean
	 */
	public boolean createTicket(Ticket ticket , User user) ;
	
	/**
	 * This method is used to update ticket.
	 * @param description String
	 * @param ticketStatus String
	 * @param ticketId  long
	 * @param user User 
	 * @return boolean
	 */
	public boolean updateTicket(String  description ,String ticketStatus,long ticketId, User user) ;
	/**
	 * This method will provide all ticket for that user either it is
	 * created by him or assign by any other . 
	 * @param userId long
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicket(long userId) ;
	
	/**
	 * This method will provide all user project for a particular 
	 * project.
	 * @param userId  long
	 * @param projectId  long
	 * @return  List<Ticket>
	 */
	public List<Ticket>  getAllTicketByProject(long userId, long projectId);
	/**
	 * This method will provide all ticket for that user based 
	 * on ticket status.
	 * @param userId long 
	 * @param status  String
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByStatus(long userId , String status);
	/**
	 * This method will provide user all ticket based on status and 
	 * project.
	 * @param userId long 
	 * @param status String
	 * @param projectId long
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByProjectAndStatus(long userId, String status, long projectId);
	/**
	 * This method will provide all user ticket based on
	 * mile stone.
	 * @param userId  long
	 * @param mileStoneId long
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByMileStone(long userId, long mileStoneId);
	
	/**
	 * This method will provide list of ticket based on user id , mile stone 
	 * and ticket status.
	 * @param userId long 
	 * @param mileStoneId long
	 * @param status String
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByMileStoneAndStatus(long userId, long mileStoneId,String status);
	
	/**
	 * This method will update mile stone status and its name.
	 * @param status String 
	 * @param mileStoneId long
	 * @param name String
	 * @return boolean
	 */ 
	public boolean updateMileStone(String status, long mileStoneId, String name);
	/**
	 * This method will provide all test case.
	 * @return  List<TestCaseResponse>
	 */
	public List<TestCaseResponse> getAllTestCase();
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
