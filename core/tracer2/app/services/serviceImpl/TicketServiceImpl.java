/**
 * 
 */
package services.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

import dataAccess.dao.TicketDao;
import dataAccess.daoFactory.DaoFactory;
import dataAccess.daoImple.TicketDaoImpl;

import responseBean.TestCaseResponse;
import services.service.*;

import util.Constants;
import util.TracerUtil;

import models.TestPhase;
import models.Ticket;
import models.User;

/**
 * @author Manzarul.Haque
 * this service class contains 
 * all ticket api related business logic
 * and after that it will call db.
 */
public class TicketServiceImpl  implements TicketService{
  private final	String sql = "select t.id as testId, t.t_case,t.exp_result,t.act_result,t.is_passed,t.created,usr.id as createrId,usr.name,p.id as projectId,p.project_name,tic.id as ticketId,tic.title from test_case t,project p, ticket tic,user usr where t.project_id=p.id  and t.ticket_id =tic.id and t.created_by_id=usr.id";
  private final	String getTestPhase = "select ph.id, ph.phase,ph.status from test_phase ph where ph.test_case_id = :testCaseId";
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
	public List<Ticket> getTicketByPidMileStoneAndStatus(int projectId, long mileStoneId, String status, int typeId) {
		boolean response = TracerUtil.checkNullOrEmpty(status);
		TicketDao ticketDao = (TicketDaoImpl)DaoFactory.getInstance(Constants.TICKET_DAO);
		List<Ticket> tickets = null;
		// here if user is sending status and type id both null/empty or zero
		// it means user needs data based on project and mile stone only.
		if (response && typeId == 0) {
			// then get ticket based on project and mile stone only
			tickets = ticketDao.getAllTicketByProjectAndMileStone(projectId, mileStoneId);
		} else if (projectId == 0 && mileStoneId > 0 && !response && typeId > 0) {
			// here we will collect all ticket based on mileStone ,
			// status,typeId
			tickets = ticketDao.getTicketByMidAndStatusAndType(mileStoneId, status, typeId);
		} else if (projectId == 0 && mileStoneId == 0 && !response && typeId > 0) {
			// then we need to fetch ticket based on status and type only.
			tickets = ticketDao.getTicketByStatusAndType(status, typeId);
		} else if (projectId == 0 && mileStoneId == 0 && response && typeId > 0) {
			// we need to collect all ticket based on type only.
			tickets = ticketDao.getTicketByType(typeId);
		} else if (projectId != 0 && mileStoneId == 0 && response && typeId > 0) {
			// we will get all ticket based on project and type id
			tickets = ticketDao.getTicketByProjectAndType(projectId, typeId);
		} else if (projectId != 0 && mileStoneId != 0 && response && typeId > 0) {
			// we will get all ticket based on project , mile stone and type id
			tickets = ticketDao.getTicketByProjectAndMidAndType(projectId, mileStoneId, typeId);
		} else if (projectId != 0 && mileStoneId != 0 && !response && typeId > 0) {
			// we will fetch all ticket based on project ,mile stone ,status and
			// type
			tickets = ticketDao.getTicketByProjectAndMidAndStatusAndType(projectId, mileStoneId, status, typeId);
		} else if (projectId == 0 && mileStoneId != 0 && !response && typeId > 0) {
			// we will fetch all ticket based on milestone , status and type
			tickets = ticketDao.getTicketByMidAndStatusAndType(mileStoneId, status, typeId);
		} else if (projectId != 0 && mileStoneId == 0 && !response && typeId > 0) {
			// we fetch list of ticket based on project status and type
			tickets = ticketDao.getTicketByProjectAndStatusAndType(projectId, status, typeId);
		} else if (projectId == 0 && mileStoneId > 0 && response && typeId > 0) {
			// we fetch list of ticket based on mile stone and type.
			tickets = ticketDao.getTicketByMidAndType(mileStoneId, typeId);
		}
		else {
			// get project based on all values.
			tickets = ticketDao.getAllTicketByProjectAndMileStoneAndStatus(projectId, mileStoneId, status);
		}
		return tickets;
	}
	
	/**
	 * this method will provide all phase 
	 * details based on project id.
	 * @param projectId
	 * @return  Map
	 */
	public Map<Integer, String> getPhaseByProject(int projectId) {
		TicketDao ticketDao =(TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return  ticketDao.getPhaseByProject(projectId);
	}
	/**
	 * This method is used to crate ticket.
	 * @param ticket Ticket object.
	 * @param user User object.
	 * @return boolean
	 */
	public boolean createTicket(Ticket ticket, User user) {
		TicketDao ticketDao = (TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return ticketDao.createTicket(ticket, user);
	}
	
	/**
	 * This method is used to update ticket.
	 * @param description String
	 * @param ticketStatus String
	 * @param ticketId long
	 * @param user User
	 * @return boolean
	 */
	public boolean updateTicket(String description, String ticketStatus, long ticketId, User user) {
		TicketDao ticketDao = (TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return ticketDao.updateticket(description, ticketStatus, ticketId, user);
	}
	
	/**
	 * This method will provide all ticket for that user either it is
	 * created by him or assign by any other . 
	 * @param userId long
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicket(long userId) {
		TicketDao ticketDao = (TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return ticketDao.getAllTicket(userId);
	}
	
	/**
	 * This method will provide all user project for a particular 
	 * project.
	 * @param userId  long
	 * @param projectId  long
	 * @return  List<Ticket>
	 */
	public List<Ticket>  getAllTicketByProject(long userId, long projectId){
		TicketDao ticketDao = (TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return ticketDao.getAllTicketByProject(userId, projectId);
	}
	
	/**
	 * This method will provide all ticket for that user based 
	 * on ticket status.
	 * @param userId long 
	 * @param status  String
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByStatus(long userId , String status){
		TicketDao ticketDao = (TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return ticketDao.getAllTicketByStatus(userId, status);
	}
	
	/**
	 * This method will provide user all ticket based on status and 
	 * project.
	 * @param userId long 
	 * @param status String
	 * @param projectId long
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByProjectAndStatus(long userId, String status, long projectId){
		TicketDao ticketDao = (TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return ticketDao.getAllTicketByProjectAndStatus(userId, status, projectId);
	}
	
	/**
	 * This method will provide all user ticket based on
	 * mile stone.
	 * @param userId  long
	 * @param mileStoneId long
	 * @return  List<Ticket>
	 */
	public List<Ticket> getAllTicketByMileStone(long userId, long mileStoneId){
		TicketDao ticketDao = (TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return ticketDao.getAllTicketByMileStone(userId, mileStoneId);
	}
	
	/**
	 * This method will provide list of ticket based on user id , mile stone 
	 * and ticket status.
	 * @param userId long 
	 * @param mileStoneId long
	 * @param status String
	 * @return List<Ticket>
	 */
	public List<Ticket> getAllTicketByMileStoneAndStatus(long userId, long mileStoneId,String status){
		TicketDao ticketDao = (TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return ticketDao.getAllTicketByMileStoneAndStatus(userId, mileStoneId, status);
	}
	
	
	/**
	 * This method will update mile stone status and its name.
	 * @param status String 
	 * @param mileStoneId long
	 * @param name String
	 * @return boolean
	 */ 
	public boolean updateMileStone(String status, long mileStoneId, String name) {
		TicketDao ticketDao = (TicketDaoImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		return ticketDao.updateMileStone(status, mileStoneId, name);
	}
	
	/**
	 * This method will provide all test case.
	 * @return  List<TestCaseResponse>
	 */
	public List<TestCaseResponse> getAllTestCase() {
		List<TestCaseResponse> testCaseResponses = new ArrayList<TestCaseResponse>();
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		List<SqlRow> list = sqlQuery.findList();
		for (int i = 0; i < list.size(); i++) {
			TestCaseResponse caseResponse = new TestCaseResponse();
			SqlRow row = list.get(i);
			caseResponse.setTestId(row.getLong("testId"));
			caseResponse.setProjectId(row.getLong("projectId"));
			caseResponse.setTicketid(row.getLong("ticketId"));
			caseResponse.setCreaterId(row.getLong("createrId"));
			caseResponse.setCreaterName(row.getString("name"));
			caseResponse.setActResult(row.getString("act_result"));
			caseResponse.setExpResult(row.getString("exp_result"));
			caseResponse.setTestCase(row.getString("t_case"));
			caseResponse.setCreatedDate(row.getString("created"));
			caseResponse.setTitle(row.getString("title"));

			SqlQuery phaseSql = Ebean.createSqlQuery(getTestPhase);
			phaseSql.setParameter("testCaseId", caseResponse.getTestId());
			List<SqlRow> phaseList = phaseSql.findList();
			List<TestPhase> phases = new ArrayList<TestPhase>();
			for (int j = 0; j < phaseList.size(); j++) {
				TestPhase phase = new TestPhase();
				SqlRow phaseRow = phaseList.get(j);
				phase.setId(phaseRow.getInteger("id"));
				phase.setPhase(phaseRow.getInteger("phase"));
				phase.setStatus(phaseRow.getBoolean("status"));
				phases.add(phase);
			}
			caseResponse.setPhase(phases);
			testCaseResponses.add(caseResponse);
		}
		return testCaseResponses;
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
	public boolean moveTicket(long ticketId,long phaseId,long mileStoneId){
	           TicketDao dao = (TicketDaoImpl)DaoFactory.getInstance(Constants.TICKET_DAO);
	           return dao.moveTicket(ticketId, phaseId, mileStoneId);
	}
}
