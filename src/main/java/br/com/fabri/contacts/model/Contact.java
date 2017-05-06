package br.com.fabri.contacts.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Contact {

	private String id;
	
	@NotBlank(message = "{validator.blank.name}")
	private String name;
	
	@NotBlank(message = "{validator.blank.phone}")
	@Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4}\\d?-\\d{4}", message = "{validator.invalid.phone}")
	private String phone;
	
	@NotBlank(message = "{validator.blank.email}")
	@Email(message = "{validator.invalid.email}")
	private String email;

	public Contact(String id, String name, String phone, String email) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public Contact() {
		this(null, null, null, null);
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
