package br.com.fabri.contacts.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.fabri.contacts.dao.UserDao;
import br.com.fabri.contacts.model.LoggedUser;
import br.com.fabri.contacts.model.User;

public class LoginControllerTest {
	
	private MockResult mockResult;
	private MockValidator mockValidator;
	private LoggedUser loggedUser;
	private UserDao userDao;
	private User user;
	
	private LoginController loginController;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void initTest() {
		this.mockResult = spy(new MockResult());
		this.mockValidator = spy(new MockValidator());
		this.loggedUser = new LoggedUser();
		this.userDao = mock(UserDao.class);
		this.user = new User();
		
		this.loginController = new LoginController(userDao, mockResult, mockValidator, loggedUser);
	}
	
	@Test
	public void whenLoginWithUserThatNotExists() {
		when(userDao.exists(user)).thenReturn(false);
		
		thrown.expect(ValidationException.class);
		
		loginController.authenticate(user);
		
		assertEquals(1, mockValidator.getErrors().size());
		assertEquals("login", mockValidator.getErrors().get(0).getCategory());
		assertEquals("login.invalid", mockValidator.getErrors().get(0).getMessage());
		verify(mockValidator).onErrorRedirectTo(LoginController.class);
	}
	
	@Test
	public void whenLoginWithUserThatExists() {
		when(userDao.exists(user)).thenReturn(true);
		
		loginController.authenticate(user);
		
		assertEquals(user, loggedUser.getUser());
		assertTrue(loggedUser.isLogged());
		verify(mockResult).redirectTo(ContactController.class);
		
	}
	
	@Test
	public void whenLogout() {
		loggedUser.setUser(user);
		
		loginController.logout();
		
		assertFalse(loggedUser.isLogged());
		verify(mockResult).redirectTo(LoginController.class);
	}
}
