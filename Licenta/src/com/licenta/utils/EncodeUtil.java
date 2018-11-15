package com.licenta.utils;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class EncodeUtil {
	private static final String ALGORITHM = "SHA-512";
	private static final String SALT = "W!Wgr@eg#de5R4$ru@3uu:8fw89e87yhgUG#PIG";
	private static String encr(String value) throws NoSuchAlgorithmException
	{
		value = addSalt(value);
		MessageDigest sha = MessageDigest.getInstance(ALGORITHM);
		byte[] arr = sha.digest(value.getBytes());
		return new String(Base64.encodeBase64(arr));
	}
	public static String sign(final String text) throws GeneralSecurityException {
		return encr(text);
	}
	private static String addSalt(String value) {
		return SALT + "231A" + value;
	}

	public static boolean verifyEquality(String taPassword, String hashedPasswordDB) {
		String hashedPassword = "";
		try {
			hashedPassword = encr(taPassword);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashedPassword.equals(hashedPasswordDB) ? true : false;
	}
	public static void main(String[] args) throws GeneralSecurityException
	{
		String password = "armand";
		String encryptedPassword = sign(password);
		boolean isSamePassword = verifyEquality(password, encryptedPassword);
		System.out.println("Password: " + password + "\n"
						+ "Encrypted password: " + encryptedPassword + "\n"
						+ "IS THE SAME PASSWORD: " + isSamePassword);
	}
}
