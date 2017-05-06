package br.com.fabri.contacts.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactValidationTest {

	private static Validator validator;
	
	private Contact contact;
	
	@BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	
	@Before
	public void initTest() {
		this.contact = new Contact("1", "test", "(21) 9999-9999", "test@gmail.com");
	}
	
	@Test
	public void whenNameIsNull() {
		contact.setName(null);
		
		Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void whenNameIsEmpty() {
		contact.setName("");
		
		Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void whenPhoneIsNull() {
		contact.setPhone(null);
		
		Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void whenPhoneIsEmpty() {
		contact.setPhone("");
		
		Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void whenPhoneIsInvalid() {
		contact.setPhone("9999-9999");
		
		Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void whenEmailIsNull() {
		contact.setEmail(null);
		
		Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void whenEmaileIsEmpty() {
		contact.setEmail("");
		
		Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void whenEmailIsInvalid() {
		contact.setEmail("this is not a email");
		
		Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
		assertFalse(violations.isEmpty());
	}
}
