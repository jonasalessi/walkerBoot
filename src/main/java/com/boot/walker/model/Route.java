package com.boot.walker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;

import com.boot.walker.modules.ClickOperation;
import com.boot.walker.modules.SleepOperation;
import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Route of navigation to user
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 * @since Nov 01, 2015
 */
public class Route implements Cloneable {

	private final List<Operation> route = new ArrayList<Operation>();
	private final WebClient client;
	private Page page;
	private String baseURL;
	
	/**
	 * @param showLog Show log in navigation
	 * @param baseURL URL main for first request
	 * @throws Exception
	 */
	public Route(boolean showLog, String baseURL) throws Exception {
		this.baseURL = baseURL;
		this.client = new WebClient();
		setHandler(this.client);
		disableWarnings();
		this.client.getOptions().setThrowExceptionOnScriptError(false);
		this.client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		this.client.getOptions().setCssEnabled(false);
		this.client.getOptions().setUseInsecureSSL(false);
		if (!showLog) {
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		}
		this.page = client.getPage(baseURL);
	}

	/**
	 * Changes the behavior of this client when a script error occurs.
	 * @param active
	 */
	public void setThrowExceptionJavaScriptError(boolean active) {
		client.getOptions().setThrowExceptionOnScriptError(active);
	}

	private void disableWarnings() {
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
	}

	private void setHandler(WebClient client2) {
		this.client.setConfirmHandler((page, message) -> {
			System.err.println(message);
			return true;
		});
		this.client.setAlertHandler((page, message) -> System.err.println(message));
		this.client.setAppletConfirmHandler(applet -> {
			System.err.println(applet);
			return true;
		});

	}

	public Route click(String id) {
		this.route.add(new ClickOperation(id));
		return this;
	}

	/**
	 * simulate the time of the thought of user.
	 * @param time Time of the sleep in milliseconds
	 * @return Route
	 */
	public Route sleep(long time) {
		this.route.add(new SleepOperation(time));
		return this;
	}

	protected List<Operation> getRoute() {
		return route;
	}

	protected Page getPage() {
		return page;
	}
	
	protected void setPage(Page page) {
		this.page = page;
	}

	public String getBaseURL() {
		return baseURL;
	}
	
	public Route add(Operation operation) {
		this.route.add(operation);
		return this;
	}
	
	public Route add(List<Operation> operation) {
		this.route.addAll(operation);
		return this;
	}

	public Route add(Operation...operations) {
		if (operations != null) {
			Collections.addAll(this.route, operations);
		}
		return this;
	}
}
