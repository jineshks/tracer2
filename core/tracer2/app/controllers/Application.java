package controllers;

import models.Ticket;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
  
    public static Result index() {
    	Ticket ticket	= Ticket.find.byId(1L);
    	if(ticket == null)
    		ticket = new Ticket();
        return ok(Json.toJson(ticket));
    }
  
}
  