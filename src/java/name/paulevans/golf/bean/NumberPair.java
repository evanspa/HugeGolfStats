package name.paulevans.golf.bean;

import java.math.BigDecimal;

/**
 * Wraps an integer and BigDecimal
 * @author pevans
 *
 */
public class NumberPair implements Comparable {

	private BigDecimal decimal;
	private int integerVal;
	
	/**
	 * Constructor
	 * @param aDecimal
	 * @param aIntegerValue
	 */
	public NumberPair(BigDecimal aDecimal, int aIntegerValue) {
		decimal = aDecimal;
		integerVal = aIntegerValue;
	}
	
	/**
	 * Returns the integer value
	 * @return
	 */
	public int getIntegerValue() {
		return integerVal;
	}
	
	/**
	 * Returns the big decimal
	 * @return
	 */
	public BigDecimal getBigDecimal() {
		return decimal;
	}
	
	/**
	 * Compares aCompareTo to this
	 */
	public int compareTo(Object aCompareTo) {
		
		NumberPair compareTo;
		
		compareTo = (NumberPair)aCompareTo;
		return decimal.compareTo(compareTo.getBigDecimal());
	}
}
