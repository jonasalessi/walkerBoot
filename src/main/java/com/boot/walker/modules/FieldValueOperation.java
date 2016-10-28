package com.boot.walker.modules;

import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

/**
 * Fill an value in element HTML.
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public class FieldValueOperation implements Operation {

	private final String value;
	private final String id;

	/**
	 * @param id of the element to be fill
	 * @param value of the element
	 */
	public FieldValueOperation(String id, String value) {
		this.id = id;
		this.value = value;
	}

	public Page execute(HtmlPage page) throws Exception {
		DomElement elementById = page.getElementById(id);
		if (elementById == null) {
			throw new Exception("In page " + page + ".Not found field: " + id);
		}
		elementById.focus();
		Thread.sleep(500);
		if (elementById instanceof HtmlInput) {
			HtmlInput input = (HtmlInput) elementById;
			input.setValueAttribute(value);
			input.blur();
			Thread.sleep(500);
			return page;
		}
		if (elementById instanceof HtmlSelect) {
			HtmlSelect select = (HtmlSelect) elementById;
			HtmlOption optionByValue = select.getOptionByValue(value);
			select.setSelectedAttribute(optionByValue, true);
			select.blur();
			Thread.sleep(500);
			return page;
		}
		throw new Exception("Not found implementation to HtmlElement:" + elementById);
	}
	
	public String toString() {
		return "Field " + id + " set value " + value;
	}

}
