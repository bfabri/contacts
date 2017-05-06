package br.com.fabri.contacts.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.fabri.contacts.model.Contact;

@Named
@ApplicationScoped
public class ContactDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Map<String, Contact> contacts;
	
	public ContactDao() {
		this.contacts = new HashMap<>();
		this.contacts.put("1", new Contact("1", "Bruno", "(21) 99520-9502", "fabri@gmail.com"));
		this.contacts.put("2", new Contact("2", "Gabi", "(21) 99990-9202", "gabi@gmail.com"));
		this.contacts.put("3", new Contact("3", "Jonas", "(21) 98526-9522", "jonas@gmail.com"));
	}
	
	public List<Contact> list() {
		return new ArrayList<>(contacts.values());
	}
	
	public void save(Contact contact) {
		contacts.put(contact.getId(), contact);
	}
	
	public void update(Contact contact) {
		contacts.put(contact.getId(), contact);
	}
	
	public void delete(Contact contact) {
		contacts.remove(contact.getId());
	}
	
	public Contact getById(String id) {
		return contacts.get(id);
	}
}
