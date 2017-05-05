package br.com.fabri.contacts.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class PasswordUtils {

	private static final String HASH_PREFIX = "contacts";
	
	private static final String SHA256_ALGORITHM = "SHA-256";

	private static final int SALT_BYTE_SIZE = 32;
	
	private static final int TOTAL_HASH_PARTS = 4;
	private static final int HASH_PREFIX_INDEX = 0;
	private static final int SALT_INDEX = 1;
	private static final int HASH_SIZE_INDEX = 2;
	private static final int HASH_INDEX = 3;

	public static String createHash(String password) throws NoSuchAlgorithmException {
		String salt = generateSalt();
		String hash = generateHash(password, salt);
		int hashSize = hash.length();
		return HASH_PREFIX + ":" + salt + ":" + hashSize + ":" + hash;
	}
	
	public static boolean isCorrectPassword(String password, String correctHash) throws NoSuchAlgorithmException {
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(correctHash)) {
			return false;
		}
		
		String[] hashParts = correctHash.split(":");
		if (hashParts.length != TOTAL_HASH_PARTS) {
			return false;
		}
		
		if (!hashParts[HASH_PREFIX_INDEX].equals(HASH_PREFIX)) {
			return false;
		}
		
		String salt = hashParts[SALT_INDEX];
		if (StringUtils.isEmpty(salt)) {
			return false;
		}
		
		String hash = hashParts[HASH_INDEX];
		if (StringUtils.isEmpty(hash)) {
			return false;
		}
		
		int hashSize = 0;
		try {
			hashSize = Integer.parseInt(hashParts[HASH_SIZE_INDEX]);
		} catch (NumberFormatException e) {
			return false;
		}
		
		if (hashSize != hash.length()) {
			return false;
		}
		
		String hashWithInTestPassword = generateHash(password, salt);
		return hashWithInTestPassword.equals(hash);
	}

	private static String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);
		return toBase64(salt);
	}

	private static String generateHash(String password, String salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(SHA256_ALGORITHM);
		
		String passwordWithSalt = salt + password;
		byte[] hash = digest.digest(passwordWithSalt.getBytes(StandardCharsets.UTF_8));
		return toBase64(hash);
	}

	private static String toBase64(byte[] array) {
		return DatatypeConverter.printBase64Binary(array);
	}
}
