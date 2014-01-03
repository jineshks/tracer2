package dataAccess.daoFactory;

import models.TestCase;
import util.Constants;
import dataAccess.dao.TestCaseDao;
import dataAccess.dao.TicketDao;
import dataAccess.dao.UserDao;
import dataAccess.daoImple.TestCaseDaoImpl;
import dataAccess.daoImple.TicketDaoImpl;
import dataAccess.daoImple.UserDaoImpl;

/**
 * @author Manzarul.Haque this class will create dao object based on incoming
 *         string value
 */
public class DaoFactory {
	private static TicketDao ticketDao = null;
	private static UserDao userDao = null;
	private static TestCaseDao  testCaseDao = null;

	/**
	 * this method will create data access object based className
	 * 
	 * @param className
	 *            name of dao class.
	 * @return Object
	 */
	public static Object getInstance(String className) {
		if (Constants.TICKET_DAO.equalsIgnoreCase(className)) {
			return getTicketDaoInstance();
		} else if (Constants.USER_DAO.equalsIgnoreCase(className)) {
			return getUserDaoInstance();
		}else if (Constants.TESTCASE_DAO.equalsIgnoreCase(className)) {
			return getTestCaseDaoInstance();
		}
		return null;
	}

	/**
	 * this method will return ticket dao singleton object.
	 * 
	 * @return TicketDao
	 */
	private static TicketDao getTicketDaoInstance() {
		if (ticketDao == null) {
			ticketDao = new TicketDaoImpl();
		}
		return ticketDao;
	}

	/**
	 * this method will return User dao singleton object.
	 * 
	 * @return TicketDao
	 */
	private static UserDao getUserDaoInstance() {
		if (userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;
	}

	/**
	 * this method will return test case dao singleton object.
	 * 
	 * @return TestCaseDao
	 */
	private static TestCaseDao getTestCaseDaoInstance() {
		if (testCaseDao == null) {
			testCaseDao = new TestCaseDaoImpl();
		}
		return testCaseDao;
	}
	
	
}
