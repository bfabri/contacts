package br.com.fabri.contacts.dao;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import br.com.fabri.contacts.model.User;
import br.com.fabri.contacts.util.PasswordUtils;

public class UserDao {

	private final DynamoDBMapper mapper;

	@Inject
	public UserDao(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}
	
	@Deprecated
	public UserDao() {
		this(null);
	}
	
	public boolean exists(User user) {
		User loadedUser = mapper.load(User.class, user.getEmail());
		if (loadedUser == null) {
			return false;
		}
		
		try {
			return PasswordUtils.isCorrectPassword(user.getPassword(), loadedUser.getPassword());
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
	}
}
