/**
 * 
 */
package service;

import java.util.List;
import java.util.Map;

import Dao.TicketDao;

import util.TracerUtil;

import models.Ticket;

/**
 * @author Manzarul.Haque
 * this service class contains 
 * all ticket api related business logic
 * and after that it will call db.
 */
public enum TicketService {
	/*
	 *  TicketService instance. 
	 */
	instance;
   
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
		TicketDao ticketDao = TicketDao.instance;
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
		return TicketDao.instance.getPhaseByProject(projectId);
	}

	
}
