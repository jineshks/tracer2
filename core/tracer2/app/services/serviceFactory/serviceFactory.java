package services.serviceFactory;

import services.service.TestCaseService;
import services.service.TicketService;
import services.service.UserService;
import services.serviceImpl.TestCaseServiceImpl;
import services.serviceImpl.TicketServiceImpl;
import services.serviceImpl.UserServiceImpl;
import util.Constants;

/**
 * 
 * @author Manzarul.Haque This class will create services object based on
 *         incoming string value.
 */
public class serviceFactory {
	private static TicketService ticketService = null;
	private static UserService userService = null;
	private static TestCaseService testCaseService = null;

	/**
	 * This method will provide Services class intance based on instance name.
	 * 
	 * @param instanceName
	 * @return Object(Service class)
	 */
	public static Object getInstance(String instanceName) {
		if (Constants.TICKET_SERVICE.equalsIgnoreCase(instanceName)) {
			return getTicketServiceInstance();
		} else if (Constants.USER_SERVICE.equalsIgnoreCase(instanceName)) {
			return getUserServiceInstance();
		}else if (Constants.TESTCASE_SERVICE.equalsIgnoreCase(instanceName)) {
			return getTestCaseServiceInstance();
		}
		return null;
	}

	/**
	 * this method will provide ticket service instance.
	 * 
	 * @return TicketService
	 */
	private static TicketService getTicketServiceInstance() {
		if (ticketService == null) {
			ticketService = new TicketServiceImpl();
		}
		return ticketService;
	}

	/**
	 * this method will provide user service instance.
	 * 
	 * @return UserService
	 */
	private static UserService getUserServiceInstance() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}

	/**
	 * this method will provide Test  service instance.
	 * 
	 * @return TestCaseService
	 */
	private static TestCaseService getTestCaseServiceInstance() {
		if (testCaseService == null) {
			testCaseService = new TestCaseServiceImpl();
		}
		return testCaseService;
	}
	
}
