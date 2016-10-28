package com.boot.walker.modules;

import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Event click in element HTML
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public class ClickOperation implements Operation {

	private final String id;
	
	/**
	 * @param id of the element HTML.
	 */
	public ClickOperation(String id){
		this.id = id;
	}

	public Page execute(HtmlPage page) throws Exception {
		DomElement element = page.getElementById(id);
		element.focus();
		Thread.sleep(500);
		Page newPage = element.click();
		return newPage;
	}

	public String toString() {
		return "Click in element " + id;
	}
}
