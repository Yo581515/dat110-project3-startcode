package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint; 
	
	public static BigInteger hashOf(String entity) {		
		
		//Kva gir deg adressen.
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		
		// we use MD5 with 128 bits digest
		byte[] bytesOfMessage;
		try {
			bytesOfMessage = entity.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] theMD5digest = md.digest(bytesOfMessage);

			// compute the hash of the input 'entity'
	        String hashtext = toHex(theMD5digest);
	        
	       	// convert the hex into BigInteger
	         hashint = new BigInteger(hashtext, 16);
			// return the BigInteger
	         
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}	
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		BigInteger addressSize;
		
		// Task: compute the address size of MD5		
		// get the digest length
		// compute the number of bits = digest length * 8
		int bits = bitSize();
		
		// compute the address size = 2 ^ number of bits
		addressSize = BigInteger.valueOf((long)Math.round(Math.pow(2, bits)));
		
		// return the address size
		return addressSize;
	}
	
	public static int bitSize() {
		
		int digestlen = 0;
		
		// find the digest length
		try {
			digestlen = MessageDigest.getInstance("MD5").digest().length;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		
		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}