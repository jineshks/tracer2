/**
 * 
 */
package service;

import java.util.List;

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
	 * @return List<Ticket>
	 */
	public List<Ticket> getTicketByPidMileStoneAndStatus(int projectId, long mileStoneId, String status) {
		boolean response = TracerUtil.checkNullOrEmpty(status);
		TicketDao ticketDao = TicketDao.instance;
		List<Ticket> tickets = null;
		if (response) {
			// then get ticket based on project and mile stone only
			tickets = ticketDao.getAllTicketByProjectAndMileStone(projectId, mileStoneId);
		} else {
			// get project based on all values.
			tickets = ticketDao.getAllTicketByProjectAndMileStoneAndStatus(projectId, mileStoneId, status);
		}
		return tickets;
	}
	
}
