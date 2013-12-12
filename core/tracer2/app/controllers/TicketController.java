package controllers;

import java.util.Date;
import java.util.List;

import models.Comment;
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
import util.Constants;
import util.JsonKey;
import util.TracerUtil;
import util.TrackLogger;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

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
	 * @return
	 */
	public static Result createTickets() {
		JsonNode json = request().body().asJson();
		Ticket ticket = parseCreateTicketData(json);
		if (ticket == null) {
			return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = Ebean.createQuery(Session.class).where().eq("sessionId", ticket.getOwner().getPassword())
		        .eq("user_id", ticket.getCreater().getId()).findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		User user = userSession.getUser();
		Project project = Ebean.createQuery(Project.class).where().eq("id", ticket.getProject().getId()).findUnique();
		MileStone mileStone = Ebean.createQuery(MileStone.class).where().eq("id", ticket.getMileStone().getId())
		        .findUnique();
		User owner = Ebean.createQuery(User.class).where().eq("id", ticket.getOwner().getId()).findUnique();
		Severity severity = Ebean.createQuery(Severity.class).where().eq("id", ticket.getSeverity().getId())
		        .findUnique();
		Complexity complexity = Ebean.createQuery(Complexity.class).where().eq("id", ticket.getComplexity().getId())
		        .findUnique();
		Phase phase = Ebean.createQuery(Phase.class).where().eq("id", ticket.getPhase().getId()).findUnique();
		if (project == null || user == null || owner == null) {
			return ok(TracerUtil.InvalidDataResponse());
		}
		ticket.setComplexity(complexity);
		ticket.setCreated(new Date());
		ticket.setCreater(user);
		ticket.setMileStone(mileStone);
		ticket.setOwner(owner);
		ticket.setPhase(phase);
		ticket.setProject(project);
		ticket.setSeverity(severity);
		ticket.setUpdated(new Date());
		Ebean.save(ticket);
		Comment comment = new Comment();
		comment.setCreated(new Date());
		comment.setText(ticket.getDescription());
		comment.setTicket(ticket);
		comment.setUser(user);
		Ebean.save(comment);
		return ok(TracerUtil.successResponse());
	}

	/**
	 * this method will update tickets.
	 * 
	 * @return
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

		Session userSession = Ebean.createQuery(Session.class).where().eq("sessionId", session).eq("user_id", userId)
		        .findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		User user = userSession.getUser();
		Ticket ticket = Ebean.createQuery(Ticket.class).where().eq("id", ticketId).findUnique();
		ticket.setTicketStatus(ticketStatus);
		ticket.setUpdated(new Date());
		Ebean.update(ticket);
		Comment comment = new Comment();
		comment.setCreated(new Date());
		comment.setText(description);
		comment.setTicket(ticket);
		comment.setUser(user);
		Ebean.save(comment);
		return ok(TracerUtil.successResponse());
	}

	/**
	 * this method will return all tickets of a particular user
	 * 
	 * @return
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

		Session userSession = Ebean.createQuery(Session.class).where().eq("sessionId", session).eq("user_id", userId)
		        .findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).findList();
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project.
	 * 
	 * @return
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

		Session userSession = Ebean.createQuery(Session.class).where().eq("sessionId", session).eq("user_id", userId)
		        .findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("project_id", projectId).findList();
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * 
	 * @return
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
		List<Ticket> tickets = Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("ticket_status", status).findList();
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * 
	 * @return
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

		List<Ticket> tickets = Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("ticket_status", status).eq("project_id", projectId).findList();
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * 
	 * @return
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
		Session userSession = Ebean.createQuery(Session.class).where().eq("sessionId", session).eq("user_id", userId)
		        .findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = Ebean.createQuery(Ticket.class)
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("mile_stone_id", mileStoneId).findList();
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method will return all tickets of a particular project based on
	 * ticket status
	 * 
	 * @return
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
			// return ok(TracerUtil.InvalidDataResponse());
		}
		Session userSession = Ebean.createQuery(Session.class).where().eq("sessionId", session).eq("user_id", userId)
		        .findUnique();
		if (userSession == null) {
			return ok(TracerUtil.invalidSessionResponse());
		}
		List<Ticket> tickets = Ebean.createQuery(Ticket.class).fetch("mileStone")
		        .where(Expr.or(Expr.eq("owner_id", userId), Expr.eq("creater_id", userId))).where()
		        .eq("mile_stone_id", mileStoneId).eq("mile_stone_status", status).findList();
		return ok(TracerUtil.successResponse(tickets));
	}

	/**
	 * this method is used to parse user requested data to ticket object.
	 * 
	 * @param json
	 * @return
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
