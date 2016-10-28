package com.boot.walker.modules;

import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Fill in the fields username and password.
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public class LoginOperation implements Operation {

	private final String idFieldUsername;
	private final String idFieldPassword;
	private final String username;
	private final String password;

	/**
	 * Pass id of username and password HTML inputs.
	 * @param idFieldUsername id field username
	 * @param idFieldPassword id field password 
	 * @param username username for login in page
	 * @param password password for login in page
	 */
	public LoginOperation(String idFieldUsername, String idFieldPassword, String username, String password) {
		this.idFieldUsername = idFieldUsername;
		this.idFieldPassword = idFieldPassword;
		this.username = username;
		this.password = password;
	}

	public Page execute(HtmlPage page)  throws Exception {
		HtmlInput htmlPass = (HtmlInput) page.getElementById(idFieldPassword);
		HtmlInput htmlUser = (HtmlInput) page.getElementById(idFieldUsername);
		if (htmlPass == null) {
			throw new Exception("In page " + page + ".Not found fieldPassword: " + idFieldPassword);
		}
		if (htmlUser == null) {
			throw new Exception("In page " + page + ".Not found fieldUsername: " + idFieldUsername);
		}
		htmlPass.setValueAttribute(password);
		htmlUser.setValueAttribute(username);
		return page;
	}

	public String toString() {
		return "Login user " + idFieldUsername + " pass " + idFieldPassword;
	}
}
