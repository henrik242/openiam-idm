/*
 * Created on Feb 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.util.encrypt
;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.util.ResourceBundle;


/**
 * SHA-256 hashing capabilty 
 * @author Suneet Shah
 *
 */
public class SHA2Hash implements HashDigest {
	static byte[] key = null;
	 static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");

	 /* (non-Javadoc)
	 * @see org.openiam.util.encrypt.HashDigest#readKey()
	 */
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
		
	/* (non-Javadoc)
	 * @see org.openiam.util.encrypt.HashDigest#hash(java.lang.String)
	 */
	public byte[] hash(String msg) {
			// get instance of the SHA Message Digest object.
			HMac hmac = new HMac(new SHA256Digest());
			byte[] result = new byte[hmac.getMacSize()];
			byte[] msgAry =  msg.getBytes() ;

			if (key == null) {
				readKey();
			}
			KeyParameter kp = new KeyParameter( key  );			
			hmac.init(kp);
			hmac.update(msgAry,0, msgAry.length);
			hmac.doFinal(result, 0);
			return result;
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.util.encrypt.HashDigest#HexEncodedHash(java.lang.String)
	 */
	public String HexEncodedHash(String msg) {
		return new String( Hex.encode(hash(msg)) );
	}
	/* (non-Javadoc)
	 * @see org.openiam.util.encrypt.HashDigest#setKey(byte[])
	 */
	public void setKey(byte[] k) {
		this.key = k;
	}
	
	public static void main(String[] args) {
		HashDigest hash = new SHA2Hash();
		String str = "Original string = test";
		System.out.println(" hash2 = " + new String ( hash.hash(str) ) );
		System.out.println(" hash2 = " + hash.HexEncodedHash(str) );

		System.out.println(" hash2 = " +
				hash.hash("Original string = yada yada yada11") );

		
	}
 }



