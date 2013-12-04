package controllers;

import models.Ticket;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
/**
 * 
 * @author Manzarul.Haque
 *
 */
public class Application extends Controller {
  /**
   * 
   * @return
   */
    public static Result index() {
    	Ticket ticket	= null;//Ticket.find.byId(1L);
    	if(ticket == null)
    		ticket = new Ticket();
        return ok(Json.toJson(ticket));
    }
  
}
  