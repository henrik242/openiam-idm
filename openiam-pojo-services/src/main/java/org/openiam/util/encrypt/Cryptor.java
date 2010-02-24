package org.openiam.util.encrypt;

/**
 * Highlevel Interface for all classes providing access to encryption algorithms. 
 * @author Suneet Shah
 *
 */
public interface Cryptor {

	public abstract String encrypt(String input);

	public abstract byte[] encryptTobyte(String input);

	public abstract String decrypt(String input);

}