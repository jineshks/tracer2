/**
 * 
 */
package dataAccess.daoImple;

import java.util.ArrayList;
import java.util.List;

import responseBean.TestCaseResponse;

import util.TrackLogger;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

import models.TestCase;
import models.TestPhase;
import dataAccess.dao.TestCaseDao;

/**
 * @author Manzarul.Haque
 *This class will override all test case dao methods and 
 *it will perform data base operation.
 */
public class TestCaseDaoImpl  implements TestCaseDao {
	private static final String className = TestCaseDaoImpl.class.getName();
	
	  private final static	String sql = "select t.id as testId, t.t_case,t.exp_result,t.act_result,t.is_passed,t.created,usr.id as createrId,usr.name," +
	  		" p.id as projectId,p.project_name,tic.id as ticketId,tic.title from test_case t,project p, ticket tic,user usr where t.project_id=p.id " +
	  		"  AND t.created_by_id=t.id AND t.ticket_id=tic.id   and t.project_id = :projectId  ";
	  private final static	String GET_TEST_CASE_BY_MILESTONE = "select t.id as testId, t.t_case,t.exp_result,t.act_result,t.is_passed,t.created,usr.id " +
	  		" as createrId,usr.name,p.id as projectId,p.project_name,tic.id as ticketId,tic.title from test_case t,project p, ticket tic,user usr,mile_stone ms  " +
	  		" where t.project_id=p.id and t.milestone_id=ms.id   AND t.created_by_id=t.id AND t.ticket_id=tic.id   and  t.milestone_id = :mileStoneId ";
	  private final static	String GET_TEST_CASE_BY_TICKET = "select t.id as testId, t.t_case,t.exp_result,t.act_result,t.is_passed,t.created,usr.id  " +
	  		" as createrId,usr.name,p.id as projectId,p.project_name,tic.id as ticketId,tic.title from test_case t,project p, ticket tic,user usr  " +
	  		" where t.project_id=p.id and t.ticket_id=tic.id AND t.created_by_id=t.id    and t.ticket_id = :ticketId ";
	  
	  private final static	String GET_TEST_CASE_BY_USER = "select t.id as testId, t.t_case,t.exp_result,t.act_result,t.is_passed,t.created,usr.id  " +
		  		" as createrId,usr.name,p.id as projectId,p.project_name,tic.id as ticketId,tic.title from test_case t,project p, ticket tic,user usr  " +
		  		" where t.project_id=p.id and t.ticket_id=tic.id  and t.created_by_id = :userId ";
	  
	  private final static	String GET_TEST_CASE_BY_USER_PROJECT = "select t.id as testId, t.t_case,t.exp_result,t.act_result,t.is_passed,t.created,usr.id  " +
		  		" as createrId,usr.name,p.id as projectId,p.project_name,tic.id as ticketId,tic.title from test_case t,project p, ticket tic,user usr  " +
		  		" where t.project_id=p.id and t.ticket_id=tic.id  and t.created_by_id = :userId  and t.project_id = :projectId";
	  private final	static String GET_TEST_CASE_BY_USER_MILESTONE = "select t.id as testId, t.t_case,t.exp_result,t.act_result,t.is_passed,t.created,usr.id  " +
		  		" as createrId,usr.name,p.id as projectId,p.project_name,tic.id as ticketId,tic.title from test_case t,project p, ticket tic,user usr,mile_stone ms   " +
		  		" where t.project_id=p.id and t.ticket_id=tic.id  and t.created_by_id = :userId  and t.milestone_id=ms.id  and t.milestone_id = :mileStoneId";
	  private final	static String getTestPhase = "select ph.id, ph.phase,ph.status from test_phase ph where ph.test_case_id = :testCaseId";
	/**
	 * This method will create Test case.
	 * @param testCase TestCase
	 * @param phase int
	 * @return boolean
	 */
	public boolean createTestCase(TestCase testCase, int phase) {
		boolean response = true;
		try {
			Ebean.beginTransaction();
			Ebean.save(testCase);
			TrackLogger.debug("test case id is ==>" + testCase.getId(), className);
			TestPhase testPhase = new TestPhase();
			testPhase.setPhase(phase);
			testPhase.setStatus(testCase.isPassed());
			testCase.setId(testCase.getId());
			testPhase.setTestCase(testCase);
			Ebean.save(testPhase);
			Ebean.commitTransaction();
		} catch (Exception e) {
			TrackLogger.error(e.getMessage(), className);
			response = false;
		Ebean.rollbackTransaction();
		}
		return response;
	}
	@Override
	public List<TestCaseResponse> getTestCaseByProject(long projectId) {
		List<TestCaseResponse> testCaseResponses = new ArrayList<TestCaseResponse>();
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		sqlQuery.setParameter("projectId", projectId);
		List<SqlRow> list = sqlQuery.findList();
		for (int i = 0; i < list.size(); i++) {
			TestCaseResponse caseResponse = new TestCaseResponse();
			SqlRow row = list.get(i);
			caseResponse.setTestId(row.getLong("testId"));
			caseResponse.setProjectId(row.getLong("projectId"));
			caseResponse.setTicketid(row.getLong("ticketId"));
			caseResponse.setCreaterId(row.getLong("createrId"));
			caseResponse.setCreaterName(row.getString("name"));
			caseResponse.setActResult(row.getString("act_result"));
			caseResponse.setExpResult(row.getString("exp_result"));
			caseResponse.setTestCase(row.getString("t_case"));
			caseResponse.setCreatedDate(row.getString("created"));
			caseResponse.setTitle(row.getString("title"));

			SqlQuery phaseSql = Ebean.createSqlQuery(getTestPhase);
			phaseSql.setParameter("testCaseId", caseResponse.getTestId());
			List<SqlRow> phaseList = phaseSql.findList();
			List<TestPhase> phases = new ArrayList<TestPhase>();
			for (int j = 0; j < phaseList.size(); j++) {
				TestPhase phase = new TestPhase();
				SqlRow phaseRow = phaseList.get(j);
				phase.setId(phaseRow.getInteger("id"));
				phase.setPhase(phaseRow.getInteger("phase"));
				phase.setStatus(phaseRow.getBoolean("status"));
				phases.add(phase);
			}
			caseResponse.setPhase(phases);
			testCaseResponses.add(caseResponse);
		}
		return testCaseResponses;
	}
	@Override
	public List<TestCaseResponse> getTestCaseByMileStone(long mileStoneId) {
		List<TestCaseResponse> testCaseResponses = new ArrayList<TestCaseResponse>();
		SqlQuery sqlQuery = Ebean.createSqlQuery(GET_TEST_CASE_BY_MILESTONE);
		sqlQuery.setParameter("mileStoneId", mileStoneId);
		List<SqlRow> list = sqlQuery.findList();
		for (int i = 0; i < list.size(); i++) {
			TestCaseResponse caseResponse = new TestCaseResponse();
			SqlRow row = list.get(i);
			caseResponse.setTestId(row.getLong("testId"));
			caseResponse.setProjectId(row.getLong("projectId"));
			caseResponse.setTicketid(row.getLong("ticketId"));
			caseResponse.setCreaterId(row.getLong("createrId"));
			caseResponse.setCreaterName(row.getString("name"));
			caseResponse.setActResult(row.getString("act_result"));
			caseResponse.setExpResult(row.getString("exp_result"));
			caseResponse.setTestCase(row.getString("t_case"));
			caseResponse.setCreatedDate(row.getString("created"));
			caseResponse.setTitle(row.getString("title"));
			SqlQuery phaseSql = Ebean.createSqlQuery(getTestPhase);
			phaseSql.setParameter("testCaseId", caseResponse.getTestId());
			List<SqlRow> phaseList = phaseSql.findList();
			List<TestPhase> phases = new ArrayList<TestPhase>();
			for (int j = 0; j < phaseList.size(); j++) {
				TestPhase phase = new TestPhase();
				SqlRow phaseRow = phaseList.get(j);
				phase.setId(phaseRow.getInteger("id"));
				phase.setPhase(phaseRow.getInteger("phase"));
				phase.setStatus(phaseRow.getBoolean("status"));
				phases.add(phase);
			}
			caseResponse.setPhase(phases);
			testCaseResponses.add(caseResponse);
		}
		return testCaseResponses;
	}
	@Override
	public List<TestCaseResponse> getTestCaseByTicket(long ticketId) {
		List<TestCaseResponse> testCaseResponses = new ArrayList<TestCaseResponse>();
		SqlQuery sqlQuery = Ebean.createSqlQuery(GET_TEST_CASE_BY_TICKET);
		sqlQuery.setParameter("ticketId", ticketId);
		List<SqlRow> list = sqlQuery.findList();
		for (int i = 0; i < list.size(); i++) {
			TestCaseResponse caseResponse = new TestCaseResponse();
			SqlRow row = list.get(i);
			caseResponse.setTestId(row.getLong("testId"));
			caseResponse.setProjectId(row.getLong("projectId"));
			caseResponse.setTicketid(row.getLong("ticketId"));
			caseResponse.setCreaterId(row.getLong("createrId"));
			caseResponse.setCreaterName(row.getString("name"));
			caseResponse.setActResult(row.getString("act_result"));
			caseResponse.setExpResult(row.getString("exp_result"));
			caseResponse.setTestCase(row.getString("t_case"));
			caseResponse.setCreatedDate(row.getString("created"));
			caseResponse.setTitle(row.getString("title"));
			SqlQuery phaseSql = Ebean.createSqlQuery(getTestPhase);
			phaseSql.setParameter("testCaseId", caseResponse.getTestId());
			List<SqlRow> phaseList = phaseSql.findList();
			List<TestPhase> phases = new ArrayList<TestPhase>();
			for (int j = 0; j < phaseList.size(); j++) {
				TestPhase phase = new TestPhase();
				SqlRow phaseRow = phaseList.get(j);
				phase.setId(phaseRow.getInteger("id"));
				phase.setPhase(phaseRow.getInteger("phase"));
				phase.setStatus(phaseRow.getBoolean("status"));
				phases.add(phase);
			}
			caseResponse.setPhase(phases);
			testCaseResponses.add(caseResponse);
		}
		return testCaseResponses;
	}
	@Override
	public List<TestCaseResponse> getTestCaseByUser(long userId) {
		List<TestCaseResponse> testCaseResponses = new ArrayList<TestCaseResponse>();
		SqlQuery sqlQuery = Ebean.createSqlQuery(GET_TEST_CASE_BY_USER);
		sqlQuery.setParameter("userId", userId);
		List<SqlRow> list = sqlQuery.findList();
		for (int i = 0; i < list.size(); i++) {
			TestCaseResponse caseResponse = new TestCaseResponse();
			SqlRow row = list.get(i);
			caseResponse.setTestId(row.getLong("testId"));
			caseResponse.setProjectId(row.getLong("projectId"));
			caseResponse.setTicketid(row.getLong("ticketId"));
			caseResponse.setCreaterId(row.getLong("createrId"));
			caseResponse.setCreaterName(row.getString("name"));
			caseResponse.setActResult(row.getString("act_result"));
			caseResponse.setExpResult(row.getString("exp_result"));
			caseResponse.setTestCase(row.getString("t_case"));
			caseResponse.setCreatedDate(row.getString("created"));
			caseResponse.setTitle(row.getString("title"));
			SqlQuery phaseSql = Ebean.createSqlQuery(getTestPhase);
			phaseSql.setParameter("testCaseId", caseResponse.getTestId());
			List<SqlRow> phaseList = phaseSql.findList();
			List<TestPhase> phases = new ArrayList<TestPhase>();
			for (int j = 0; j < phaseList.size(); j++) {
				TestPhase phase = new TestPhase();
				SqlRow phaseRow = phaseList.get(j);
				phase.setId(phaseRow.getInteger("id"));
				phase.setPhase(phaseRow.getInteger("phase"));
				phase.setStatus(phaseRow.getBoolean("status"));
				phases.add(phase);
			}
			caseResponse.setPhase(phases);
			testCaseResponses.add(caseResponse);
		}
		return testCaseResponses;
	}
	@Override
	public List<TestCaseResponse> getTestCaseByProjectAndUser(long projectId, long userId) {
		List<TestCaseResponse> testCaseResponses = new ArrayList<TestCaseResponse>();
		SqlQuery sqlQuery = Ebean.createSqlQuery(GET_TEST_CASE_BY_USER_PROJECT);
		sqlQuery.setParameter("userId", userId);
		sqlQuery.setParameter("projectId", projectId);
		List<SqlRow> list = sqlQuery.findList();
		for (int i = 0; i < list.size(); i++) {
			TestCaseResponse caseResponse = new TestCaseResponse();
			SqlRow row = list.get(i);
			caseResponse.setTestId(row.getLong("testId"));
			caseResponse.setProjectId(row.getLong("projectId"));
			caseResponse.setTicketid(row.getLong("ticketId"));
			caseResponse.setCreaterId(row.getLong("createrId"));
			caseResponse.setCreaterName(row.getString("name"));
			caseResponse.setActResult(row.getString("act_result"));
			caseResponse.setExpResult(row.getString("exp_result"));
			caseResponse.setTestCase(row.getString("t_case"));
			caseResponse.setCreatedDate(row.getString("created"));
			caseResponse.setTitle(row.getString("title"));
			SqlQuery phaseSql = Ebean.createSqlQuery(getTestPhase);
			phaseSql.setParameter("testCaseId", caseResponse.getTestId());
			List<SqlRow> phaseList = phaseSql.findList();
			List<TestPhase> phases = new ArrayList<TestPhase>();
			for (int j = 0; j < phaseList.size(); j++) {
				TestPhase phase = new TestPhase();
				SqlRow phaseRow = phaseList.get(j);
				phase.setId(phaseRow.getInteger("id"));
				phase.setPhase(phaseRow.getInteger("phase"));
				phase.setStatus(phaseRow.getBoolean("status"));
				phases.add(phase);
			}
			caseResponse.setPhase(phases);
			testCaseResponses.add(caseResponse);
		}
		return testCaseResponses;
	}
	@Override
	public List<TestCaseResponse> getTestCaseByUserAndMileStone(long mileStone, long userId) {
		List<TestCaseResponse> testCaseResponses = new ArrayList<TestCaseResponse>();
		SqlQuery sqlQuery = Ebean.createSqlQuery(GET_TEST_CASE_BY_USER_MILESTONE);
		sqlQuery.setParameter("userId", userId);
		sqlQuery.setParameter("mileStoneId", mileStone);
		List<SqlRow> list = sqlQuery.findList();
		for (int i = 0; i < list.size(); i++) {
			TestCaseResponse caseResponse = new TestCaseResponse();
			SqlRow row = list.get(i);
			caseResponse.setTestId(row.getLong("testId"));
			caseResponse.setProjectId(row.getLong("projectId"));
			caseResponse.setTicketid(row.getLong("ticketId"));
			caseResponse.setCreaterId(row.getLong("createrId"));
			caseResponse.setCreaterName(row.getString("name"));
			caseResponse.setActResult(row.getString("act_result"));
			caseResponse.setExpResult(row.getString("exp_result"));
			caseResponse.setTestCase(row.getString("t_case"));
			caseResponse.setCreatedDate(row.getString("created"));
			caseResponse.setTitle(row.getString("title"));
			SqlQuery phaseSql = Ebean.createSqlQuery(getTestPhase);
			phaseSql.setParameter("testCaseId", caseResponse.getTestId());
			List<SqlRow> phaseList = phaseSql.findList();
			List<TestPhase> phases = new ArrayList<TestPhase>();
			for (int j = 0; j < phaseList.size(); j++) {
				TestPhase phase = new TestPhase();
				SqlRow phaseRow = phaseList.get(j);
				phase.setId(phaseRow.getInteger("id"));
				phase.setPhase(phaseRow.getInteger("phase"));
				phase.setStatus(phaseRow.getBoolean("status"));
				phases.add(phase);
			}
			caseResponse.setPhase(phases);
			testCaseResponses.add(caseResponse);
		}
		return testCaseResponses;
	}
}
