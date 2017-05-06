package br.com.fabri.contacts.interceptor;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.fabri.contacts.controller.LoginController;
import br.com.fabri.contacts.model.LoggedUser;
import br.com.fabri.contacts.util.Public;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AuthInterceptorTest {

	private MockResult mockResult;
	private LoggedUser loggedUser;
	private ControllerMethod controllerMethod;
	
	private AuthInterceptor authInterceptor;
	private SimpleInterceptorStack stack;
	
	@Before
	public void initTest() {
		this.mockResult = spy(new MockResult());
		this.loggedUser = new LoggedUser();
		this.controllerMethod = mock(ControllerMethod.class);
		this.stack = mock(SimpleInterceptorStack.class);
		
		this.authInterceptor = new AuthInterceptor(mockResult, loggedUser, controllerMethod);
	}
	
	@Test
	public void whenAcceptsInterceptor() {
		when(controllerMethod.containsAnnotation(Public.class)).thenReturn(false);
		assertTrue(authInterceptor.accepts());
	}
	
	@Test
	public void whenNotAcceptsInterceptor() {
		when(controllerMethod.containsAnnotation(Public.class)).thenReturn(true);
		assertFalse(authInterceptor.accepts());
	}
	
	@Test
	public void whenInterceptWhileUserIsNotLogged() {
		when(controllerMethod.containsAnnotation(Public.class)).thenReturn(false);
		authInterceptor.intercept(stack);
		
		verify(mockResult).redirectTo(LoginController.class);
	}
}
