package controllers;

import java.util.List;
import java.util.Map;

import models.Complexity;
import models.MileStone;
import models.Phase;
import models.Project;
import models.Session;
import models.Severity;
import models.Ticket;
import models.User;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import responseBean.TestCaseResponse;
import services.service.TicketService;
import services.serviceFactory.serviceFactory;
import services.serviceImpl.TicketServiceImpl;
import util.Constants;
import util.JsonKey;
import util.TracerUtil;
import util.TrackLogger;
import dataAccess.daoFactory.DaoFactory;

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
		TicketService ticketService = (TicketServiceImpl) DaoFactory.getInstance(Constants.TICKET_SERVICE);
		boolean response = ticketService.createTicket(ticket, user);
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
		TicketService ticketService = (TicketServiceImpl) DaoFactory.getInstance(Constants.TICKET_SERVICE);
		boolean response = ticketService.updateTicket(description, ticketStatus, ticketId, user);
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
		TicketService ticketService = (TicketServiceImpl) DaoFactory.getInstance(Constants.TICKET_DAO);
		List<Ticket> tickets = ticketService.getAllTicket(userId);
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
		TicketService ticketService = (TicketServiceImpl) DaoFactory.getInstance(Constants.TICKET_SERVICE);
		List<Ticket> tickets = ticketService.getAllTicketByProject(userId, projectId);
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
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		TicketService ticketService = (TicketServiceImpl) DaoFactory.getInstance(Constants.TICKET_SERVICE);
		List<Ticket> tickets = ticketService.getAllTicketByStatus(userId, status);
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
		TicketService ticketService = (TicketServiceImpl) DaoFactory.getInstance(Constants.TICKET_SERVICE);
		List<Ticket> tickets = ticketService.getAllTicketByProjectAndStatus(userId, status, projectId);
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
		TicketService ticketService = (TicketServiceImpl) DaoFactory.getInstance(Constants.TICKET_SERVICE);
		List<Ticket> tickets = ticketService.getAllTicketByMileStone(userId, mileStoneId);
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
		TicketService ticketService = (TicketServiceImpl) DaoFactory.getInstance(Constants.TICKET_SERVICE);
		List<Ticket> tickets = ticketService.getAllTicketByMileStoneAndStatus(userId, mileStoneId, status);
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
		TicketService ticketService = (TicketServiceImpl) DaoFactory.getInstance(Constants.TICKET_SERVICE);
		boolean updateStatus = ticketService.updateMileStone(status, mileStoneId, name);
		if (updateStatus) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}

	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * 
	 * @return Result
	 */
	public static Result getTicketByPidMileStoneAndStatus() {
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
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		TicketService ticketService = (TicketServiceImpl) serviceFactory.getInstance(Constants.TICKET_SERVICE);
		List<Ticket> tickets = ticketService.getTicketByPidMileStoneAndStatus(projectId, mileStoneId, status, typeId);
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all test case
	 * 
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
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		TicketService ticketService = (TicketServiceImpl) serviceFactory.getInstance(Constants.TICKET_SERVICE);
		List<TestCaseResponse> testCaseResponses = ticketService.getAllTestCase();
		return ok(TracerUtil.successResponse(testCaseResponses));
	}

	/**
	 * this method will return all phase for a particular project.
	 * 
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
		TicketService ticketService = (TicketServiceImpl) serviceFactory.getInstance(Constants.TICKET_SERVICE);
		Map<Integer, String> phaseMap = ticketService.getPhaseByProject(projectId);
		return ok(TracerUtil.successResponse(phaseMap));
	}

	
	/**
	 * this method will be called when user move ticket from
	 * one phase to another phase or move it from one mile stone
	 * to another.
	 * @return Result
	 */
	public static Result moveTicket() {
		JsonNode json = request().body().asJson();
		long userId = 0;
		String session = null;
		long ticketId = 0L;
		long phaseId = 0l;
		long mileStoneId = 0L;
		try {
			session = json.get(JsonKey.SESSION).asText();
			userId = json.get(JsonKey.USER_ID).asLong();
			ticketId = json.get(JsonKey.TICKET_ID).asLong();
			phaseId = json.get(JsonKey.PHASE_ID).asLong();
			mileStoneId = json.get(JsonKey.MILE_STONE_ID).asLong();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = TracerUtil.checkSession(session, userId);
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		TicketService ticketService = (TicketServiceImpl) serviceFactory.getInstance(Constants.TICKET_SERVICE);
		boolean response = ticketService.moveTicket(ticketId, phaseId, mileStoneId);
		if (response) {
			return ok(TracerUtil.successResponse());
		}
		return ok(TracerUtil.failureResponse());
	}

	
	
	/**
	 * this method is used to parse user requested data to ticket object.
	 * 
	 * @param json
	 *            JsonNode
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
