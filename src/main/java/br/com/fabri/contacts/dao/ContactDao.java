package br.com.fabri.contacts.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import br.com.fabri.contacts.model.Contact;

@RequestScoped
public class ContactDao {

	private final DynamoDBMapper mapper;

	@Inject
	public ContactDao(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}
	
	@Deprecated
	public ContactDao() {
		this(null);
	}
	
	public List<Contact> list() {
		return mapper.scan(Contact.class, new DynamoDBScanExpression());
	}
	
	public void save(Contact contact) {
		mapper.save(contact);
	}
	
	public void delete(Contact contact) {
		mapper.delete(contact);
	}
	
	public Contact getById(String id) {
		return mapper.load(Contact.class, id);
	}
}
