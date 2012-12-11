package name.paulevans.golf.util;


/**
 * Utility class for performing encryption
 * @author pevans
 *
 */
public interface EncryptionUtils {
	
	/**
	 * Digests the inputted value and returns the result
	 * @param aInput
	 * @return
	 */
	String digest(String aInput);
}
