package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.Session;
import models.Ticket;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.TracerUtil;

public class UserController  extends Controller {
	
	 public static Result login() {
		 	String userName="manzarul07@gmail.com";
		 	String password ="12qwaszx";
		  List<User> user	= Ebean.createQuery(User.class).where().eq("email", userName).eq("password", password).findList();
		  if(user != null && user.size()>0 ) {
			  Session session = null;
		     session =	Ebean.createQuery(Session.class).where().eq("user_id", user.get(0).getId()).findUnique();
		     if(session != null){
		    Ebean.delete(session);
		     }
		    session = new  Session();
		    session.setSessionId( TracerUtil.getUniqueId(user.get(0).getEmail()));
		    Ebean.save(session);
		  }
		  System.out.print(user);
	    	Ticket ticket	= Ticket.find.byId(1L);
	    	if(ticket == null)
	    		ticket = new Ticket();
	        return ok(Json.toJson(ticket));
	    }
}
