package com.boot.walker.modules.operation;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * HTML DOM query selector
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 * @since Oct 05, 2016
 */
public class QuerySelector implements Selector {

	private String querySelector;

	public QuerySelector(String querySelector) {
		this.querySelector = querySelector;
	}

	public String search(HtmlPage page) {
		DomNode element = page.querySelector(querySelector);
		if (element == null) {
			throw new ElementNotFoundException(querySelector, null, null);
		}
		NamedNodeMap attributes = element.getAttributes();
		Node src = attributes.getNamedItem("src");
		if (src == null) {
			throw new ElementNotFoundException(element.getNodeName(), "src", null);
		}
		String link = src.getNodeValue();
		return link;
	}

}
