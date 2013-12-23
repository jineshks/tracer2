/**
 * 
 */
package responseBean;

import java.util.List;

import models.TestPhase;

/**
 * @author Manzarul.Haque
 *this bean will provide test case response data.
 */
public class TestCaseResponse {
	private long testId;
	private long projectId;
	private long ticketid;
	private String testCase;
	private String expResult;
	private String actResult;
	private List<TestPhase> phase;
	private String createrName ;
	private String title;
	private long createrId;
	private String createdDate ;
	/**
	 * @return the testId
	 */
	public long getTestId() {
		return testId;
	}
	/**
	 * @param testId the testId to set
	 */
	public void setTestId(long testId) {
		this.testId = testId;
	}
	/**
	 * @return the projectId
	 */
	public long getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the ticketid
	 */
	public long getTicketid() {
		return ticketid;
	}
	/**
	 * @param ticketid the ticketid to set
	 */
	public void setTicketid(long ticketid) {
		this.ticketid = ticketid;
	}
	/**
	 * @return the testCase
	 */
	public String getTestCase() {
		return testCase;
	}
	/**
	 * @param testCase the testCase to set
	 */
	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}
	/**
	 * @return the expResult
	 */
	public String getExpResult() {
		return expResult;
	}
	/**
	 * @param expResult the expResult to set
	 */
	public void setExpResult(String expResult) {
		this.expResult = expResult;
	}
	/**
	 * @return the actResult
	 */
	public String getActResult() {
		return actResult;
	}
	/**
	 * @param actResult the actResult to set
	 */
	public void setActResult(String actResult) {
		this.actResult = actResult;
	}
	
	/**
	 * @return the phase
	 */
	public List<TestPhase> getPhase() {
		return phase;
	}
	/**
	 * @param phase the phase to set
	 */
	public void setPhase(List<TestPhase> phase) {
		this.phase = phase;
	}
	/**
	 * @return the createrName
	 */
	public String getCreaterName() {
		return createrName;
	}
	/**
	 * @param createrName the createrName to set
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the createrId
	 */
	public long getCreaterId() {
		return createrId;
	}
	/**
	 * @param createrId the createrId to set
	 */
	public void setCreaterId(long createrId) {
		this.createrId = createrId;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
