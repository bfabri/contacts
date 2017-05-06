package br.com.fabri.contacts.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import br.com.fabri.contacts.model.User;
import br.com.fabri.contacts.util.PasswordUtils;

public class UserDaoTest {

	private DynamoDBMapper mapper;
	private UserDao userDao;
	private User user;
	
	@Before
	public void initTest() throws NoSuchAlgorithmException {
		this.mapper = mock(DynamoDBMapper.class);
		this.userDao = new UserDao(mapper);
		this.user = new User("teste@gmail.com", PasswordUtils.createHash("123change"));
	}
	
	@Test
	public void whenNotExistsUserInDB() {
		when(mapper.load(User.class, user.getEmail())).thenReturn(null);
		
		assertFalse(userDao.exists(user));
	}
	
	@Test
	public void whenPasswordIsIncorrect() {
		User searchedUser = new User("teste@gmail.com", "opss");
		
		when(mapper.load(User.class, user.getEmail())).thenReturn(user);
		
		assertFalse(userDao.exists(searchedUser));
	}
	
	@Test
	public void whenPasswordIsCorrect() {
		User searchedUser = new User("teste@gmail.com", "123change");
		
		when(mapper.load(User.class, user.getEmail())).thenReturn(user);
		
		assertTrue(userDao.exists(searchedUser));
	}
	
}
