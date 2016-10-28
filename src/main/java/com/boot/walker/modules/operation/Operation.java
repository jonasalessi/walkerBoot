package com.boot.walker.modules.operation;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Interface for implementation of operation
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public interface Operation {

	Page execute(HtmlPage page) throws Exception;
}
