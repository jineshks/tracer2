/**
 * 
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * @author Manzarul.Haque
 * this class contains test iteration and 
 * result of each iteration.
 */
@Entity
@Table(name="test_phase")
public class TestPhase  extends Model{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	private int phase;
    private boolean status;
    @Id
    private long id;
    @Column(name="test_case_id")
    @OneToMany
    private TestCase testCase;
	/**
	 * @return the phase
	 */
	public int getPhase() {
		return phase;
	}
	/**
	 * @param phase the phase to set
	 */
	public void setPhase(int phase) {
		this.phase = phase;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the testCase
	 */
	public TestCase getTestCase() {
		return testCase;
	}
	/**
	 * @param testCase the testCase to set
	 */
	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}
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
	
}
