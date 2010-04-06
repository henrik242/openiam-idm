package org.openiam.util.encrypt;

import java.util.ResourceBundle;
import java.io.*;

import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.util.encoders.*;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;


/**
 * Class to represent a cryptographic key
 * @author Suneet Shah
 *
 */
public class CryptKey {

	 static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");

	byte[] salt = Hex.decode("3D83C0E4546AC140");
	int count = 5;
	
	/**
	 * Generates a secure key that is used by other encryption engines.
	 * @param password
	 * @return
	 */
	public byte[] createKey(String password) {
		if (password == null) {
			throw new NullPointerException("Password parameter is null.");
		}
		
		PBEParametersGenerator  generator = new PKCS12ParametersGenerator(new SHA1Digest());
		generator.init(PBEParametersGenerator.PKCS12PasswordToBytes(password.toCharArray()),salt,count);
		CipherParameters  key = generator.generateDerivedParameters(24 * 8);
		 return ((KeyParameter)key).getKey() ;		
	}	
	
	/**
	 * Writes the key to a file.
	 * @param key
	 */
	public void saveKey(byte[] key) throws FileNotFoundException  {
		String path = res.getString("MS_KEY_LOC");
		String filename = "cayo.dat";
		try {
			BufferedOutputStream stream =  new BufferedOutputStream(new FileOutputStream(path + "/" + filename));
			stream.write(key);
			stream.flush();
			stream.close();
		}catch(IOException io) {
			io.printStackTrace();
		}
	}

	// for testing purposes.
	public static void main(String[] args) {
		CryptKey ky = new CryptKey();
		byte[] ary = ky.createKey("sasny2578");
		try {
		ky.saveKey(ary);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
}
