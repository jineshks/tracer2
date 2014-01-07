/**
 * 
 */
package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.test.WithApplication;
import util.TrackLogger;

/**
 * this test class will run user test.
 * @author Manzarul.Haque
 *
 */
public class UserTest extends WithApplication {
	
	private static final String className = UserTest.class.getName();
	/**
	 * this method will run before running test cases.
	 * and it will create in memory data base.
	 */
   @Before
    public  void  setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
    /**
     * This test case will first insert one user and then check user is 
     * inserted or not
     */
    @Test
    public void createAndRetrieveUser() {
    	 running(fakeApplication(inMemoryDatabase()), new Runnable() {
             public void run() {
            		User user = new User();
                	user.setEmail("bob@gmail.com");
                	user.setName("Bob");
                	user.setPassword("secrte");
                	user.setPhone("1234567890");
                	user.setId(10);
                    user.save();
                    User bob = User.find.where().eq("email", "bob@gmail.com").findUnique();
                    TrackLogger.debug("getteing data==>"+bob.getName() +"  " +bob.getId() , className);
                    assertNotNull(bob);
                    assertEquals("Bob", bob.getName());
             }
         });
    }
    
   /**
    * This method will check user credentials. 
    */
   @Test 
  public void loginSuccessTest() {
		 running(fakeApplication(inMemoryDatabase()), new Runnable() {
             public void run() {
            		User user = new User();
                	user.setEmail("bob@gmail.com");
                	user.setName("Bob");
                	user.setPassword("secrte");
                	user.setPhone("1234567890");
                	user.setId(10);
                    user.save();
                    User bob = User.find.where().eq("email", "bob@gmail.com").eq("pwd", "secrte").findUnique();
             	   TrackLogger.debug("getting loging response data==>" + bob.getEmail(), className);
             	   assertNotNull(bob);
                    assertEquals("Bob", bob.getName());
             }
         });
  }
  
   /**
    * This method will check user credentials. 
    */
   @Test 
  public void loginFailureTest() {
		 running(fakeApplication(inMemoryDatabase()), new Runnable() {
             public void run() {
            		User user = new User();
                	user.setEmail("bob@gmail.com");
                	user.setName("Bob");
                	user.setPassword("secrte");
                	user.setPhone("1234567890");
                	user.setId(10);
                    user.save();
                    User bob = User.find.where().eq("email", "manzarul07@mail.com").eq("pwd", "secrte").findUnique();
             	   TrackLogger.debug("getting loging response data==>" + bob, className);
             	   Assert.assertNull(bob);
             }
         });
  }
   
}