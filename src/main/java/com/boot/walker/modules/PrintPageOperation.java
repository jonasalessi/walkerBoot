package com.boot.walker.modules;

import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Print HTML in console.
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public class PrintPageOperation implements Operation {

	private String html;
	
	public Page execute(HtmlPage page) throws Exception {
		this.html = page.asXml();
		System.out.println(html);
		return page;
	}
	
	public String getHTML() {
		return this.html;
	}
	
	@Override
	public String toString() {
		return html;
	}

}
