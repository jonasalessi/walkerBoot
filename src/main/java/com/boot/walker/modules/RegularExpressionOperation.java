package com.boot.walker.modules;

import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Search for a value with the regular expression
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public class RegularExpressionOperation implements Operation {

	private final String regularExpression;

	public RegularExpressionOperation(String regularExpression) {
		this.regularExpression = regularExpression;
	}
	
	public Page execute(HtmlPage page) throws Exception {
		if (page.asText().matches(regularExpression)) {
			return page;
		}
		throw new Exception("Not contains value " + regularExpression);
	}
	
	public String toString() {
		return "Contains " + regularExpression;
	}

}
