/**
 * 
 */
package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.data.format.Formats;
import play.db.ebean.Model;
import util.DateUtil;

/**
 * @author Manzarul.Haque
 *this model contains details about test cases.
 */
@Entity
@Table(name="test_case")
public class TestCase  extends Model{
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	@Id
   private long id ;  
	@OneToOne
	private Project project;
	@OneToOne
	private Ticket ticket;
	@Column (name="t_case")
	private String testCase; 
	@Column(name="exp_result")
	private String expectedResult;
	@Column (name = "act_result")
	private String actualResult;
	@Column (name = "is_passed")
	private boolean isPassed;
	@Column(name = "created_by_id")
	@OneToOne
	private User createdBy;
	 @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
	    private Date created;
	 @Column(name = "milestone_id")
	 private long mileStoneId;

	 /**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}
	/**
	 * @return the ticket
	 */
	public Ticket getTicket() {
		return ticket;
	}
	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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
	 * @return the expectedResult
	 */
	public String getExpectedResult() {
		return expectedResult;
	}
	/**
	 * @param expectedResult the expectedResult to set
	 */
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	/**
	 * @return the actualResult
	 */
	public String getActualResult() {
		return actualResult;
	}
	/**
	 * @param actualResult the actualResult to set
	 */
	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}
	/**
	 * @return the isPassed
	 */
	public boolean isPassed() {
		return isPassed;
	}
	/**
	 * @param isPassed the isPassed to set
	 */
	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return DateUtil.getFormattedDateWithTimeZone(created);
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * @return the mileStoneId
	 */
	public long getMileStoneId() {
		return mileStoneId;
	}
	/**
	 * @param mileStoneId the mileStoneId to set
	 */
	public void setMileStoneId(long mileStoneId) {
		this.mileStoneId = mileStoneId;
	}
}
