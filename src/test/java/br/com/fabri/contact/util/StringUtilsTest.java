package br.com.fabri.contact.util;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.fabri.contacts.util.StringUtils;

public class StringUtilsTest {

	@Test
	public void whenStringIsEmptyUsingNullValue() {
		assertTrue(StringUtils.isEmpty(null));
	}
	
	@Test
	public void whenStringIsEmptyUsingEmptyString() {
		assertTrue(StringUtils.isEmpty(""));
	}
	
	@Test
	public void whenStringIsNotEmpty() {
		assertFalse(StringUtils.isEmpty("notempty"));
	}
}
