package zcu.pia.bohmannd.utils;

/**
 * Encoding and validating password
 */
public interface Encoder {

	/**
	 * Returns hash of the passed text.
	 * 
	 * @param text String to encode
	 * @return encoded string
	 */
	String encode(String text);

	/**
	 * Validates that the text is the plaintext form associated with the hash.
	 * 
	 * @param text
	 *            plaintext form
	 * @param hash
	 *            hash for comparison
	 * @return true of correct
	 */
	boolean validate(String text, String hash);
}
