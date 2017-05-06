package br.com.fabri.contacts.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.fabri.contacts.dao.ContactDao;
import br.com.fabri.contacts.model.Contact;

public class ContactControllerTest {

	private MockResult mockResult;
	private MockValidator mockValidator;
	private ContactDao contactDao;
	private Contact contact;
	
	private ContactController contactController;
	
	@Before
	public void initTest() {
		this.mockResult = spy(new MockResult());
		this.mockValidator = spy(new MockValidator());
		this.contactDao = mock(ContactDao.class);
		this.contact = new Contact("1", "test", "(21) 9999-9999", "test@gmail.com");
		
		this.contactController = new ContactController(mockResult, mockValidator, contactDao);
	}
	
	@Test
	public void whenListContacts() {
		when(contactDao.list()).thenReturn(Arrays.asList(contact));
		
		contactController.list();
		
		List<Contact> result = mockResult.included("contacts");
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(contact.getId(), result.get(0).getId());
		assertEquals(contact.getName(), result.get(0).getName());
		assertEquals(contact.getPhone(), result.get(0).getPhone());
		assertEquals(contact.getEmail(), result.get(0).getEmail());
	}
	
	@Test
	public void whenCallForm() {
		contactController.form(contact);
		
		Contact result = mockResult.included("contact");
		
		assertNotNull(result);
		assertEquals(contact.getId(), result.getId());
		assertEquals(contact.getName(), result.getName());
		assertEquals(contact.getPhone(), result.getPhone());
		assertEquals(contact.getEmail(), result.getEmail());
	}
	
	@Test
	public void whenEditUser() {
		when(contactDao.getById(contact.getId())).thenReturn(contact);
		
		contactController.edit(contact);
		
		verify(mockResult).forwardTo(ContactController.class);
	}
	
	@Test
	public void whenDeleteUser() {
		doNothing().when(contactDao).delete(contact);
		
		contactController.delete(contact);
		
		assertNotNull(mockResult.included("message"));
		assertEquals("Contato removido com sucesso", mockResult.included("message"));
		verify(mockResult).redirectTo(ContactController.class);
	}
	
	@Test
	public void whenSaveContact() {
		doNothing().when(contactDao).save(contact);
		
		contactController.save(contact);
		
		assertNotNull(mockResult.included("message"));
		assertEquals("Contato salvo com sucesso", mockResult.included("message"));
		verify(mockResult).redirectTo(ContactController.class);
	}
}
