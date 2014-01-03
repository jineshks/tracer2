/**
 * 
 */
package controllers;

import java.util.Date;
import java.util.List;

import models.Project;
import models.Session;
import models.TestCase;
import models.Ticket;
import models.User;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import responseBean.TestCaseResponse;
import services.service.TestCaseService;
import services.service.TicketService;
import services.serviceFactory.serviceFactory;
import services.serviceImpl.TestCaseServiceImpl;
import services.serviceImpl.TicketServiceImpl;
import util.Constants;
import util.JsonKey;
import util.TracerUtil;
import util.TrackLogger;
import dataAccess.daoFactory.DaoFactory;

/**
 * @author Manzarul.Haque
 *
 */
public class TestCaseController  extends Controller {
	private static final String className = TicketController.class.getName();
	
	/**
	 * this method will create test cases.
	 * @return Result
	 */
	public static Result createTestCase() {
		long userId = 0l;
		String session;
		long ticketId = 0l;
		String testCase;
		String expResult;
		String actResult;
		long mileStoneId;
		int phaseId;
		boolean status;
		long projectId;
		try {
			JsonNode json = request().body().asJson();
			testCase = json.get(JsonKey.TEST_CASE).asText();
			expResult = json.get(JsonKey.EXPECETED_RESULT).asText();
			session = json.get(JsonKey.SESSION).asText();
			ticketId = json.get(JsonKey.TICKET_ID).asLong();
			userId = json.get(JsonKey.USER_ID).asLong();
			projectId = json.get(JsonKey.PROJECT_ID).asLong();
			actResult = json.get(JsonKey.ACTUAL_RESULT).asText();
			mileStoneId = json.get(JsonKey.MILE_STONE_ID).asLong();
			phaseId = json.get(JsonKey.PHASE_ID).asInt();
			status = json.get(JsonKey.STATUS).asBoolean();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		TestCase tcases = wrapeTestCaseData(testCase, expResult, actResult, projectId, mileStoneId, ticketId, userId,
		        status);
		TestCaseService testService = (TestCaseServiceImpl) serviceFactory.getInstance(Constants.TESTCASE_SERVICE);
		boolean response = testService.createTestCase(tcases, phaseId);
		if (response) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}
	
	/**
	 * this method will create test cases.
	 * @return Result
	 */
	public static Result getTestCases() {
		long userId = 0l;
		String session;
		long ticketId = 0l;
		long mileStoneId;
		long projectId;
		boolean isMine;
		try {
			JsonNode json = request().body().asJson();
			session = json.get(JsonKey.SESSION).asText();
			ticketId = json.get(JsonKey.TICKET_ID).asLong();
			userId = json.get(JsonKey.USER_ID).asLong();
			projectId = json.get(JsonKey.PROJECT_ID).asLong();
			mileStoneId = json.get(JsonKey.MILE_STONE_ID).asLong();
			isMine = json.get(JsonKey.IS_MINE).asBoolean();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		TestCaseService testService = (TestCaseServiceImpl) serviceFactory.getInstance(Constants.TESTCASE_SERVICE);
		List<TestCaseResponse> caseResponses = testService
		        .getTestCase(userId, mileStoneId, projectId, ticketId, isMine);
		if (caseResponses != null && caseResponses.size() > 0) {
			return ok(TracerUtil.successResponse(caseResponses));
		}
		return ok(TracerUtil.failureResponse());
	}
	

	/**
	 * This method will wrap all data inside TestCase class.
	 * @param testCase String
	 * @param expResult String
	 * @param actResult String
	 * @param projectId long
	 * @param mileStoneId long
	 * @param ticketId long
	 * @param userId long
	 * @param status boolean
	 * @return  TestCase
	 */
	private static TestCase wrapeTestCaseData(String testCase,String expResult,String actResult,long projectId,long mileStoneId,long ticketId,long userId,boolean status) {
		TestCase tcase = new TestCase();
		tcase.setTestCase(testCase);
		tcase.setActualResult(actResult);
		tcase.setExpectedResult(expResult);
		tcase.setCreated(new Date());
		User user = new User();
		user.setId(userId);
		tcase.setCreatedBy(user);
		Project project = new Project();
		project.setId(projectId);
		tcase.setProject(project);
		tcase.setMileStoneId(mileStoneId);
		tcase.setPassed(status);
		Ticket ticket = new Ticket();
		ticket.setId(ticketId);
		tcase.setTicket(ticket);
		return tcase;
	}

}
