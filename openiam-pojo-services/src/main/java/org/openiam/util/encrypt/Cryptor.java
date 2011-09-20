package org.openiam.util.encrypt;



import org.openiam.exception.EncryptionException;

/**
 * Highlevel Interface for all classes providing access to encryption algorithms. 
 * @author Suneet Shah
 *
 */
public interface Cryptor {

	public abstract String encrypt(String input)  throws EncryptionException;

	public abstract byte[] encryptTobyte(String input);

	public abstract String decrypt(String input)  throws EncryptionException;

}