/**
 * 
 */
package services.serviceImpl;

import java.util.List;

import dataAccess.dao.TestCaseDao;
import dataAccess.daoFactory.DaoFactory;
import dataAccess.daoImple.TestCaseDaoImpl;
import models.TestCase;
import responseBean.TestCaseResponse;
import services.service.TestCaseService;
import services.serviceFactory.serviceFactory;
import util.Constants;

/**
 * @author Manzarul.Haque
 * This class will implement all test case service
 * methods.and perform business logic if required.
 *
 */
public class TestCaseServiceImpl implements TestCaseService {

	/**
	 * This method will create Test case.
	 * @param testCase TestCase
	 * @param phase int
	 * @return boolean
	 */
	public boolean createTestCase(TestCase testCase,int phase){
		TestCaseDao testcaseDao = (TestCaseDaoImpl) DaoFactory.getInstance(Constants.TESTCASE_DAO);
		return testcaseDao.createTestCase(testCase, phase);
	}
	
	/**
	 * This method will provide list of test cases 
	 * combination of all options.
	 * @param userId  long
	 * @param mileStoneId long
	 * @param projectId long
	 * @param ticketId long
	 * @param isMine boolean
	 * @return  List<TestCase>
	 */
	public List<TestCaseResponse> getTestCase(long userId,long mileStoneId,long projectId,long ticketId,boolean isMine){
		   //here we will check all possible combinations.
			if(ticketId>0 && !isMine ) {
				//then send all test case for that ticket and for that user only.
			} else if(projectId> 0 && mileStoneId ==0 ) {
				//then send all test cases for that project
			} else if(projectId==0 &&mileStoneId>0) {
				//send all test cases for that mile stone only.
			}else if(projectId >0 &&mileStoneId>0 && !isMine) {
				//send all test cases for that project and mile stone 
			} else if(projectId >0 &&mileStoneId>0 && isMine) {
				//send all test cases for that mile stone and  project id and which is created by me only.
			}else if(projectId >0 &&mileStoneId==0 && isMine) {
				//send all test cases for that  project id and which is created by me only.
			}else if(projectId == 0 &&mileStoneId>0 && isMine) {
				//send all test cases for that  mile stone  and which is created by me only.
			}
			else if (mileStoneId==0 && ticketId == 0 && projectId == 0 && userId >0) {
				//send all test cases for particular user only.
			} else {
				//send all test cases
			}
		return null;
	}
}
