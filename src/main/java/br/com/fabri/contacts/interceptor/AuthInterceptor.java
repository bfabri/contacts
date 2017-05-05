package br.com.fabri.contacts.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.fabri.contacts.controller.LoginController;
import br.com.fabri.contacts.model.LoggedUser;
import br.com.fabri.contacts.util.Public;

@Intercepts
public class AuthInterceptor {
	private final Result result;
    private final LoggedUser loggedUser;
    private final ControllerMethod controllerMethod;

    @Inject
    public AuthInterceptor(Result result, LoggedUser loggedUser, ControllerMethod controllerMethod) {
        this.result = result;
		this.loggedUser = loggedUser;
		this.controllerMethod = controllerMethod;
    }

    @Deprecated
    AuthInterceptor() {
        this(null, null, null); // CDI eyes only
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {

        if (!loggedUser.isLogged()) {
            result.redirectTo(LoginController.class).form();
            return;
        }
        stack.next();
    }
    
    @Accepts
    public boolean accepts() {
    	return !controllerMethod.containsAnnotation(Public.class);
    }
}
