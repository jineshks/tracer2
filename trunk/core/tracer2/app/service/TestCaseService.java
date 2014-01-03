package services.service;

import java.util.List;

import responseBean.TestCaseResponse;

import models.TestCase;

/**
 * This service contains all test case related methods.
 * @author Manzarul.Haque
 *
 */
public interface TestCaseService {
	/**
	 * This method will create Test case.
	 * @param testCase TestCase
	 * @param phase int
	 * @return boolean
	 */
	public boolean createTestCase(TestCase testCase,int phase);
	
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
	public List<TestCaseResponse> getTestCase(long userId,long mileStoneId,long projectId,long ticketId,boolean isMine);

} 
