package br.com.fabri.contact.util;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import static org.junit.Assert.*;

import br.com.fabri.contacts.util.PasswordUtils;

public class PasswordUtilsTest {

	@Test
	public void whenPasswordMatches() throws NoSuchAlgorithmException {
		String generatedHash = PasswordUtils.createHash("mynewpassword123");
		assertTrue(PasswordUtils.isCorrectPassword("mynewpassword123", generatedHash));
	}
	
	@Test
	public void whenPasswordNotMatches() throws NoSuchAlgorithmException {
		String generatedHash = PasswordUtils.createHash("mynewpassword123");
		assertFalse(PasswordUtils.isCorrectPassword("bla", generatedHash));
	}
}
