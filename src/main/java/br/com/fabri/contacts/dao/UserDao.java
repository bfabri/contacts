package br.com.fabri.contacts.dao;

import java.security.NoSuchAlgorithmException;

import br.com.fabri.contacts.model.User;
import br.com.fabri.contacts.util.PasswordUtils;

public class UserDao {

	private User user;
	
	public UserDao() {
		 try {
			user = new User("fabri@gmail.com", PasswordUtils.createHash("Q1w2e3r4"));
		} catch (NoSuchAlgorithmException e) {
			user = new User("fabri@gmail.com", "blah");
		}
	}
	
	public boolean exists(User user) {
		try {
			return this.user.getEMail().equals(user.getEMail()) && PasswordUtils.isCorrectPassword(user.getPassword(), this.user.getPassword());
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
	}
}
