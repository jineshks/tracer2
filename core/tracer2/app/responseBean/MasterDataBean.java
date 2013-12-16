package responseBean;

import java.util.List;

import models.Complexity;
import models.MileStone;
import models.Phase;
import models.Severity;
import models.Type;

/**
 * this class contains all
 * helper class information.
 * @author Manzarul.Haque
 *
 */
public class MasterDataBean {
	List<Severity>   severity  = null;
	List<Type>  types = null;
	List<Phase> phases = null;
	List<Complexity> complexities = null;
	MileStone  mileStone = null;
	List<String> ticketStatus = null;
	/**
	 * @return the severity
	 */
	public List<Severity> getSeverity() {
		return severity;
	}
	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(List<Severity> severity) {
		this.severity = severity;
	}
	/**
	 * @return the types
	 */
	public List<Type> getTypes() {
		return types;
	}
	/**
	 * @param status the types to set
	 */
	public void setTypes(List<Type> status) {
		this.types = status;
	}
	/**
	 * @return the phases
	 */
	public List<Phase> getPhases() {
		return phases;
	}
	/**
	 * @param phases the phases to set
	 */
	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}
	/**
	 * @return the complexities
	 */
	public List<Complexity> getComplexities() {
		return complexities;
	}
	/**
	 * @param complexities the complexities to set
	 */
	public void setComplexities(List<Complexity> complexities) {
		this.complexities = complexities;
	}
	/**
	 * @return the mileStone
	 */
	public MileStone getMileStone() {
		return mileStone;
	}
	/**
	 * @param mileStone the mileStone to set
	 */
	public void setMileStone(MileStone mileStone) {
		this.mileStone = mileStone;
	}
	/**
	 * @return the ticketStatus
	 */
	public List<String> getTicketStatus() {
		return ticketStatus;
	}
	/**
	 * @param ticketStatus the ticketStatus to set
	 */
	public void setTicketStatus(List<String> ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
}
