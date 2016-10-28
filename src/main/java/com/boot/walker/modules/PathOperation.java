package com.boot.walker.modules;

import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Way to go.
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public class PathOperation implements Operation {

	private final String path;
	
	/**
	 * Pass the link to access.
	 * @param path link to access
	 */
	public PathOperation(String path) {
		this.path = path;
	}

	public Page execute(HtmlPage page) throws Exception {
		Page newPage = page.getWebClient().getPage(path);
		int status = newPage.getWebResponse().getStatusCode();
		if (status < 200 || status > 299) {
			throw new Exception(path + ": " + newPage.getWebResponse().getStatusMessage());
		}
		return newPage;
	}

	public String toString() {
		return "Route " + path;
	}
}
