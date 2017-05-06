package br.com.fabri.contacts.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoggedUserTest {

	private LoggedUser loggedUser;
	
	@Before
	public void initTest() {
		this.loggedUser = new LoggedUser();
	}
	
	@Test
	public void whenUserIsLogged() {
		User user = new User();
		loggedUser.setUser(user);
		
		assertEquals(user, loggedUser.getUser());
		assertTrue(loggedUser.isLogged());
	}
	
	@Test
	public void whenUserIsNotLogged() {
		assertFalse(loggedUser.isLogged());
	}
}
