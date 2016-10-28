package com.boot.walker.modules;

import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Run a script with JavaScript
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public class ScriptOperation implements Operation {

	private final String javaScript;

	public ScriptOperation(String javaScript) {
		this.javaScript = javaScript;
	}

	public Page execute(HtmlPage page) throws Exception {
		Page newPage = page.executeJavaScript(javaScript).getNewPage();
		return newPage;
	}

}
