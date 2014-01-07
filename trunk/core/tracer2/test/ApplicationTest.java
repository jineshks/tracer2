import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.POST;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.route;
import static play.test.Helpers.running;
import static play.test.Helpers.status;

import models.User;

import org.junit.Test;

import play.libs.Json;
import play.mvc.Result;
import play.test.FakeRequest;
import util.JsonKey;
import util.TrackLogger;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
import org.codehaus.jackson.node.ObjectNode;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

	   @Test
	    public void login() {
	        running(fakeApplication(inMemoryDatabase()), new Runnable() {
	            public void run() {
	            	User user = new User();
	            	user.setEmail("bob@gmail.com");
	            	user.setName("Bob");
	            	user.setPassword("secrte");
	            	user.setPhone("1234567890");
	            	user.setId(1);
	                user.save();
	                ObjectNode loginJson = Json.newObject();
	                loginJson.put(JsonKey.USER_NAME,"bob@gmail.com");
	                loginJson.put(JsonKey.PASSWORD, "secrte");
	                FakeRequest request = new FakeRequest(POST, "/v1/login").withJsonBody(loginJson);
	                Result result = route(request);
	                TrackLogger.debug(Json.toJson(Json.parse(contentAsString(result))).toString(),"ApplicationTest");
	                assertThat(status(result)).isEqualTo(OK);

	           /*     JsonNode json = Json.parse(contentAsString(result));
	                System.out.println("OUTPUT :: " +json.get("authToken"));
	                assertThat(json.get("authToken")).isNotNull();*/
	            }
	        });
	    }

	   /* @Test
	    public void loginWithBadPassword() {
	        running(fakeApplication(inMemoryDatabase()), new Runnable() {
	            public void run() {
	                DemoData.loadDemoData();

	                ObjectNode loginJson = Json.newObject();
	                loginJson.put("emailAddress", DemoData.user1.getEmailAddress());
	                loginJson.put("password", DemoData.user1.getPassword().substring(1));

	                Result result = callAction(routes.ref.SecurityController.login(), fakeRequest().withJsonBody(loginJson));

	                assertThat(status(result)).isEqualTo(UNAUTHORIZED);
	            }
	        });
	    }

	    @Test
	    public void loginWithBadUsername() {
	        running(fakeApplication(inMemoryDatabase()), new Runnable() {
	            public void run() {
	                DemoData.loadDemoData();

	                ObjectNode loginJson = Json.newObject();
	                loginJson.put("emailAddress", DemoData.user1.getEmailAddress().substring(1));
	                loginJson.put("password", DemoData.user1.getPassword());

	                Result result = callAction(routes.ref.SecurityController.login(), fakeRequest().withJsonBody(loginJson));

	                assertThat(status(result)).isEqualTo(UNAUTHORIZED);
	            }
	        });
	    }

	    @Test
	    public void loginWithDifferentCaseUsername() {
	        running(fakeApplication(inMemoryDatabase()), new Runnable() {
	            public void run() {
	                DemoData.loadDemoData();

	                ObjectNode loginJson = Json.newObject();
	                loginJson.put("emailAddress", DemoData.user1.getEmailAddress().toUpperCase());
	                loginJson.put("password", DemoData.user1.getPassword());

	                Result result = callAction(routes.ref.SecurityController.login(), fakeRequest().withJsonBody(loginJson));

	                assertThat(status(result)).isEqualTo(OK);
	            }
	        });
	    }

	    @Test
	    public void loginWithNullPassword() {
	        running(fakeApplication(inMemoryDatabase()), new Runnable() {
	            public void run() {
	                DemoData.loadDemoData();

	                ObjectNode loginJson = Json.newObject();
	                loginJson.put("emailAddress", DemoData.user1.getEmailAddress());

	                Result result = callAction(routes.ref.SecurityController.login(), fakeRequest().withJsonBody(loginJson));

	                assertThat(status(result)).isEqualTo(BAD_REQUEST);
	            }
	        });
	    }

	    @Test
	    public void logout() {
	        running(fakeApplication(inMemoryDatabase()), new Runnable() {
	            public void run() {
	                DemoData.loadDemoData();

	                String authToken = DemoData.user1.createToken();

	                Result result = callAction(routes.ref.SecurityController.logout(), fakeRequest().withHeader(SecurityController.AUTH_TOKEN_HEADER, authToken));

	                assertThat(status(result)).isEqualTo(SEE_OTHER);
	            }
	        });
	    }*/


}
