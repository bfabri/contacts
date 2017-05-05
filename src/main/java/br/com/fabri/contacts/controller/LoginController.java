package br.com.fabri.contacts.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.fabri.contacts.dao.UserDao;
import br.com.fabri.contacts.model.LoggedUser;
import br.com.fabri.contacts.model.User;

@Controller
public class LoginController {

	private final UserDao userDao;
	private final Result result;
	private final Validator validator;
	private final LoggedUser loggedUser;

	@Inject
	public LoginController(UserDao userDao, Result result, Validator validator, LoggedUser loggedUser) {
		this.userDao = userDao;
		this.result = result;
		this.validator = validator;
		this.loggedUser = loggedUser;
	}

	@Deprecated
	LoginController() {
		this(null, null, null, null); // CDI eyes only
	}

	@Get
	public void form() {
	}

	@Post
	public void authenticate(User user) {
		if (!userDao.exists(user)) {
			validator.add(new I18nMessage("login", "login.invalid"));
			validator.onErrorRedirectTo(this).form();
		}
		loggedUser.setUser(user);
		result.redirectTo(ContactController.class).init();
	}
}
