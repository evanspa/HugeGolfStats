package name.paulevans.golf.bean;

import name.paulevans.golf.dao.TeeDAO;

/**
 * Wraps a Tee object and provides additional functionality.
 * @author Paul
 *
 */
public class TeeWrapperBean {
	
	// wrapped tee object...
	private TeeDAO tee;
	
	private int totalDistance;
	private int totalPar;
	
	/**
	 * public constructor
	 * @param aTee
	 */
	public TeeWrapperBean(TeeDAO aTee) {
		tee = aTee;
	}

	/**
	 * Getter
	 * @return
	 */
	public TeeDAO getTee() {
		return tee;
	}

	/**
	 * Setter
	 * @param aTee
	 */
	public void setTee(TeeDAO aTee) {
		this.tee = aTee;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public int getTotalPar() {
		return totalPar;
	}

	public void setTotalPar(int totalPar) {
		this.totalPar = totalPar;
	}

}
