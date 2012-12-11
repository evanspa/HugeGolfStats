package name.paulevans.golf.util.impl;

import name.paulevans.golf.util.EncryptionUtils;

import org.apache.catalina.realm.RealmBase;

/**
 * Implementation of EncryptionUtils
 * @author pevans
 *
 */
public class EncryptionUtilsImpl implements EncryptionUtils {
	
	private String encryptionAlgorithm;
	
	/**
	 * Sets the encryption algorithm
	 * @param aAlgorithm
	 */
	public void setEncryptionAlgorithm(String aAlgorithm) {
		encryptionAlgorithm = aAlgorithm;
	}

	/**
	 * Digests the inputted value and returns the result
	 * @param aInput
	 * @return
	 */
	public String digest(String aInput) {
		return RealmBase.Digest(aInput, encryptionAlgorithm, null);
	}
}
