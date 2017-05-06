package br.com.fabri.contacts.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.fabri.contacts.dao.UserDao;
import br.com.fabri.contacts.model.LoggedUser;
import br.com.fabri.contacts.model.User;
import br.com.fabri.contacts.util.Public;

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

	@Public
	@Get
	@Path("/login")
	public void form() {
	}

	@Public
	@Post
	@Path("/login")
	public void authenticate(User user) {
		if (!userDao.exists(user)) {
			validator.add(new I18nMessage("login", "login.invalid"));
			validator.onErrorRedirectTo(this).form();
		}
		loggedUser.setUser(user);
		result.redirectTo(ContactController.class).list();
	}
	
	@Get
	@Path("/logout")
    public void logout() {
		loggedUser.setUser(null);
		result.redirectTo(this).form();
    }
}
