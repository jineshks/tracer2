package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Complexity;
import models.MileStone;
import models.Phase;
import models.Project;
import models.Session;
import models.Severity;
import models.TestPhase;
import models.Ticket;
import models.User;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import responseBean.TestCaseResponse;
import service.TicketService;
import util.Constants;
import util.JsonKey;
import util.TracerUtil;
import util.TrackLogger;
import Dao.TicketDao;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

/**
 * this api will control all ticket related actions.
 * 
 * @author Manzarul.Haque
 * 
 */
public class TicketController extends Controller {
	private static final String className = TicketController.class.getName();

	/**
	 * this method will create new tickets.
	 * 
	 * @return Result
	 */
	public static Result createTickets() {
		JsonNode json = request().body().asJson();
		Ticket ticket = parseCreateTicketData(json);
		if (ticket == null) {
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(ticket.getOwner().getPassword(), ticket.getCreater().getId());

		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		User user = userSession.getUser();
		boolean response = TicketDao.instance.createTicket(ticket, user);
		if (response) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}

	/**
	 * this method will update tickets.
	 * 
	 * @return Result
	 */
	public static Result updateTickets() {
		JsonNode json = request().body().asJson();
		long userId;
		String session;
		long ticketId;
		String description;
		String ticketStatus;
		try {
			description = json.get(JsonKey.DESCRIPTION).asText();
			ticketStatus = json.get(JsonKey.STATUS).asText();
			session = json.get(JsonKey.SESSION).asText();
			ticketId = json.get(JsonKey.TICKET_ID).asLong();
			userId = json.get(JsonKey.USER_ID).asLong();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}

		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		User user = userSession.getUser();
		boolean response = TicketDao.instance.updateticket(description, ticketStatus, ticketId, user);
		if (response) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}

	/**
	 * this method will return all tickets of a particular user
	 * 
	 * @return Result
	 */
	public static Result getAllTickets() {
		JsonNode json = request().body().asJson();
		long userId = 0l;
		String session = null;
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = TicketDao.instance.getAllTicket(userId);
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project.
	 * 
	 * @return Result
	 */
	public static Result getAllProjectTickets() {
		JsonNode json = request().body().asJson();
		long userId = 0;
		String session = null;
		long projectId = 0l;
		try {
			session = json.get(JsonKey.SESSION).asText();
			projectId = json.get(JsonKey.PROJECT_ID).asLong();
			userId = json.get(JsonKey.USER_ID).asLong();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}

		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = TicketDao.instance.getAllTicketByProject(userId, projectId);
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * 
	 * @return Result
	 */
	public static Result getAllTicketsByStatus() {
		JsonNode json = request().body().asJson();
		long userId = 0l;
		String session = null;
		String status = Constants.TicketStatus.active.toString();
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			status = json.get(JsonKey.STATUS).asText();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = Ebean.createQuery(Session.class).where().eq("sessionId", session).eq("user_id", userId)
		        .findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = TicketDao.instance.getAllTicketByStatus(userId, status);
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * 
	 * @return Result
	 */
	public static Result getTicketsByProjectAndStatus() {
		JsonNode json = request().body().asJson();
		long userId = 0;
		String session = null;
		String status = Constants.TicketStatus.active.toString();
		long projectId = 0;
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			status = json.get(JsonKey.STATUS).asText();
			projectId = json.get(JsonKey.PROJECT_ID).asLong();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = TicketDao.instance.getAllTicketByProjectAndStatus(userId, status, projectId);
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * 
	 * @return Result
	 */
	public static Result getTicketsByMileStone() {
		JsonNode json = request().body().asJson();
		long userId = 0;
		String session = null;
		long mileStoneId = 0;
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			mileStoneId = json.get(JsonKey.MILE_STONE_ID).asLong();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = TicketDao.instance.getAllTicketByMileStone(userId, mileStoneId);
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * 
	 * @return Result
	 */
	public static Result getTicketsByMileStoneAndStatus() {
		JsonNode json = request().body().asJson();
		long userId = 0;
		String session = null;
		long mileStoneId = 0L;
		String status = Constants.TicketStatus.active.toString();
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			mileStoneId = json.get(JsonKey.MILE_STONE_ID).asLong();
			status = json.get(JsonKey.STATUS).asText();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = TicketDao.instance.getAllTicketByMileStoneAndStatus(userId, mileStoneId, status);
		return ok(TracerUtil.successResponse(tickets));
	}

	
	
	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * @return Result
	 */
	public static Result updateMileStone() {
		JsonNode json = request().body().asJson();
		long userId = 0;
		String session = null;
		long mileStoneId = 0L;
		String status = null;
		String name = null;
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			mileStoneId = json.get(JsonKey.MILE_STONE_ID).asLong();
			status = json.get(JsonKey.STATUS).asText();
			name = json.get(JsonKey.NAME).asText();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		boolean updateStatus = TicketDao.instance.updateMileStone(status, mileStoneId,name);
		if (updateStatus) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}
  
	
	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * @return Result
	 */
	public static Result getTicketByPidMileStoneAndStatus() {
		JsonNode json = request().body().asJson();
		long userId = 0;
		String session = null;
		long mileStoneId = 0;
		String status = "";
		int projectId =1;
		int typeId  = 2;
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			mileStoneId = json.get(JsonKey.MILE_STONE_ID).asLong();
			status = json.get(JsonKey.STATUS).asText();
			projectId = json.get(JsonKey.PROJECT_ID).asInt();
			typeId = json.get(JsonKey.TYPE_ID).asInt();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket>  tickets = TicketService.instance.getTicketByPidMileStoneAndStatus(projectId, mileStoneId,status,typeId);
		return ok(TracerUtil.successResponse(tickets));
	}
	
	/**
	 * this method will return all test case
	 * @return Result
	 */
	public static Result getAllTestCase() {
		JsonNode json = request().body().asJson();
		long userId = 0;
		String session = null;
		long mileStoneId = 0;
		String status = "";
		int projectId = 1;
		int typeId = 2;
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			mileStoneId = json.get(JsonKey.MILE_STONE_ID).asLong();
			status = json.get(JsonKey.STATUS).asText();
			projectId = json.get(JsonKey.PROJECT_ID).asInt();
			typeId = json.get(JsonKey.TYPE_ID).asInt();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			// return ok(TracerUtil.InvalidDataResponse());
		}
		/*
		 * Session userSession = TracerUtil.checkSession(session, userId); if
		 * (userSession == null) { return
		 * ok(TracerUtil.invalidSessionResponse()); }
		 */
		String sql = "select t.id as testId, t.t_case,t.exp_result,t.act_result,t.is_passed,t.created,usr.id as createrId,usr.name,p.id as projectId,p.project_name,tic.id as ticketId,tic.title from test_case t,project p, ticket tic,user usr where t.project_id=p.id  and t.ticket_id =tic.id and t.created_by_id=usr.id";
		String getTestPhase = "select ph.id, ph.phase,ph.status from test_phase ph where ph.test_case_id = :testCaseId";
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
		return ok(TracerUtil.successResponse(testCaseResponses));
	}

	/**
	 * this method will return all phase for a
	 * particular project.
	 * @return Result
	 */
	public static Result getPhaseByProject() {
		JsonNode json = request().body().asJson();
		long userId = 0;
		String session = null;
		int projectId = 1;
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			projectId = json.get(JsonKey.PROJECT_ID).asInt();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		Map<Integer, String> phaseMap = TicketService.instance.getPhaseByProject(projectId);
		return ok(TracerUtil.successResponse(phaseMap));
	}
	
	/**
	 * this method is used to parse user requested data to ticket object.
	 * 
	 * @param json JsonNode
	 * @return Result
	 */
	private static Ticket parseCreateTicketData(JsonNode json) {
		Ticket ticket = null;
		try {
			ticket = new Ticket();
			Project project = new Project();
			User creater = new User();
			User owner = new User();
			Phase phase = new Phase();
			MileStone mileStone = new MileStone();
			Severity severity = new Severity();
			Complexity complexity = new Complexity();
			ticket.setTitle(json.get(JsonKey.TITLE).asText());
			ticket.setDescription(json.get(JsonKey.DESCRIPTION).asText());
			ticket.setTicketStatus(json.get(JsonKey.STATUS).asText());
			ticket.setActulHours(json.get(JsonKey.ACTUAL_HOURS).asDouble());
			ticket.setEstimatedHours(json.get(JsonKey.ESTIMATED_HOURS).asDouble());
			// here we are setting session id inside phone number for internal
			// use only.
			owner.setPassword(json.get(JsonKey.SESSION).asText());
			project.setId(json.get(JsonKey.PROJECT_ID).asLong());
			creater.setId(json.get(JsonKey.USER_ID).asLong());
			phase.setId(json.get(JsonKey.PHASE_ID).asLong());
			owner.setId(json.get(JsonKey.OWNER_ID).asLong());
			mileStone.setId(json.get(JsonKey.MILE_STONE_ID).asLong());
			severity.setId(json.get(JsonKey.SEVERITY).asLong());
			complexity.setId(json.get(JsonKey.COMPLEXITY_ID).asLong());
			ticket.setOwner(owner);
			ticket.setCreater(creater);
			ticket.setPhase(phase);
			ticket.setProject(project);
			ticket.setComplexity(complexity);
			ticket.setSeverity(severity);
			ticket.setMileStone(mileStone);
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			ticket = null;
		}
		return ticket;
	}

}
