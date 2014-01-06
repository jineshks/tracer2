/**
 * 
 */
package dataAccess.dao;

import java.util.List;

import responseBean.TestCaseResponse;

import models.TestCase;

/**
 * @author Manzarul.Haque
 *this interface contains all test case data base related 
 *methods.
 */
public interface TestCaseDao {

	/**
	 * This method will create Test case.
	 * @param testCase TestCase
	 * @param phase int
	 * @return boolean
	 */
	public boolean createTestCase(TestCase testCase,int phase);
	/**
	 * This method will provide list of test cases based on project.
	 * @param projectId long
	 * @return  List<TestCaseResponse>
	 */
	public List<TestCaseResponse> getTestCaseByProject(long projectId);
	/**
	 * This method will provide Test cases based on mile Stone
	 * @param mileStoneId long
	 * @return List<TestCaseResponse>
	 */
	public List<TestCaseResponse> getTestCaseByMileStone(long mileStoneId);
	/**
	 * This method will provide list of test cases based on ticket.
	 * @param ticketId long
	 * @return List<TestCaseResponse>
	 */
	public List<TestCaseResponse> getTestCaseByTicket(long ticketId);
	/**
	 * This method will provide test case based on user id.
	 * @param userId long
	 * @return List<TestCaseResponse>
	 */
	public List<TestCaseResponse> getTestCaseByUser(long userId);
	
	/**
	 * This method will provide list of test cases based on project and user
	 * @param projectId long
	 * @param userId long
	 * @return  List<TestCaseResponse>
	 */
	public List<TestCaseResponse> getTestCaseByProjectAndUser(long projectId,long userId);
	
	/**
	 * This method will provide list of test cases based on mile stone and user
	 * @param mileStone long
	 * @param userId long
	 * @return  List<TestCaseResponse>
	 */
	public List<TestCaseResponse> getTestCaseByUserAndMileStone(long mileStone,long userId);
	
	/**
	 * This method will call test case dao and update
	 * test case.
	 * @param testCaseId long
	 * @param expectResult String
	 * @param actResult String
	 * @param isPassed boolean
	 * @return boolean
	 */
	public boolean updateTestCase(long testCaseId,String expectResult,String actResult,boolean isPassed);

}
