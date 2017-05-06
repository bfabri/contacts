package br.com.fabri.contacts.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.fabri.contacts.dao.ContactDao;
import br.com.fabri.contacts.model.Contact;

@Controller
public class ContactController {

	private final Result result;
	private final Validator validator;
	private final ContactDao contactDao;
	
	@Inject
	public ContactController(Result result, Validator validator, ContactDao contactDao) {
		this.result = result;
		this.validator = validator;
		this.contactDao = contactDao;
	}
	
	@Deprecated
	ContactController() {
		this(null, null, null); // CDI eyes only
	}

	@Get
	@Path("/")
    public void list() {
    	List<Contact> contacts = contactDao.list();
    	result.include("contacts", contacts);
    }
	
	@Get
	@Path("/form")
	public void form(Contact contact) {
		result.include("contact", contact);
	}
	
	@Get
	@Path("/{contact.id}/edit")
	public void edit(Contact contact) {
		contact = contactDao.getById(contact.getId());
		result.forwardTo(this).form(contact);
	}
	
	@Post
	@Path("/save")
	public void save(@Valid Contact contact) {
		validator.onErrorRedirectTo(this).form(contact);
		
		contactDao.save(contact);
		
		result.include("message", "Contato salvo com sucesso");
		result.redirectTo(this).list();
	}
	
	@Post
	@Path("/delete")
	public void delete(Contact contact) {
		contactDao.delete(contact);
		
		result.include("message", "Contato removido com sucesso");
		result.redirectTo(this).list();
	}
}
