/*
 * Created on Apr 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.util.encrypt;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.crypto.paddings.*;
import org.bouncycastle.crypto.engines.*;
import org.bouncycastle.crypto.modes.*;
import org.bouncycastle.util.encoders.*;


/**
 * DESedeCryptor provides 3DES Encryption
 * @author Suneet Shah
 * 
 */
public class DESedeCryptor implements Cryptor {

	private byte[] key = null;
	private BufferedBlockCipher cipher = null;
	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	   
	public void readKey() {
		String path = res.getString("MS_KEY_LOC");
		String filename = "cayo.dat";
		try {
			BufferedInputStream stream =  new BufferedInputStream(new FileInputStream(path + "/" + filename));
			int len = stream.available();
			key = new byte[len];
			stream.read(key, 0,len);
			stream.close();
		}catch(IOException io) {
			io.printStackTrace();
		}
	}
	
	public void setKey(byte[] ky) {
		key = ky;
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.util.encrypt.Cryptor#encrypt(java.lang.String)
	 */
	public String encrypt(String input) {
		if (key == null) {
			readKey();
		}

		KeyParameter kp = new KeyParameter(key);
		cipher = new PaddedBufferedBlockCipher(	new CBCBlockCipher(new DESedeEngine()));
		cipher.init(true, kp);
		
		byte[] inputByteAry = input.getBytes();
		byte[] result = new byte[cipher.getOutputSize(inputByteAry.length)];
		int len = cipher.processBytes(inputByteAry, 0, inputByteAry.length, result, 0);

		try {
		 len += cipher.doFinal(result, len);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String encValue = new String(Hex.encode(result, 0, len));
		return encValue;
	
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.util.encrypt.Cryptor#encryptTobyte(java.lang.String)
	 */
	public byte[] encryptTobyte(String input) {
		if (key == null) {
			readKey();
		}

		KeyParameter kp = new KeyParameter(key);
		cipher = new PaddedBufferedBlockCipher(	new CBCBlockCipher(new DESedeEngine()));
		cipher.init(true, kp);
		
		byte[] inputByteAry = input.getBytes();
		byte[] result = new byte[cipher.getOutputSize(inputByteAry.length)];
		int len = cipher.processBytes(inputByteAry, 0, inputByteAry.length, result, 0);

		try {
		 len += cipher.doFinal(result, len);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		

		
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.util.encrypt.Cryptor#decrypt(java.lang.String)
	 */
	public String decrypt(String input) {
		if (key == null) {
			readKey();
		}
		KeyParameter kp = new KeyParameter(key);
		cipher = new PaddedBufferedBlockCipher(	new CBCBlockCipher(new DESedeEngine()));
		cipher.init(false, kp);
		
		byte[] inputByteAry =  Hex.decode(input);
		byte[] result = new byte[cipher.getOutputSize(inputByteAry.length)];
        int len = cipher.processBytes(inputByteAry, 0, inputByteAry.length, result, 0);
        try {
        	len += cipher.doFinal(result,len);        	
        }catch(Exception e) {
        	e.printStackTrace();
        }      
 		return new String(result,0,len);
	}
	

	
	public static void main(String[] args) {
		Cryptor des = new DESedeCryptor();
		System.out.println("Original string = your moma");
		String encString =  des.encrypt("your moma3433");
		System.out.println("encrypted string = " + encString);

		String decString =  des.decrypt(encString);
		System.out.println("decrypted string = " + decString);

		
	}
}
