package test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash256 {

	private static byte[] hashBytes(String originalString) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(originalString.getBytes());
			return encodedhash;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	private static String bytesToBase64(String originalString) {
			return Base64.getUrlEncoder().encodeToString(originalString.getBytes() );
		 
	}
	private static byte[] base64ToBytes(String encoded) {
		return Base64.getUrlDecoder().decode(encoded);
	 
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		byte[] hashbytes= hashBytes("www.paytm.com/abc");
		String str= bytesToBase64("www.paytm.com/abc");
		System.out.println("hashbytes: " + hashbytes);
		System.out.println("base64 string: " + str);
		System.out.println(base64ToBytes(str));
	}

}
