package com.boot.walker.modules;

import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Sleep the requisition in determined time.
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public class SleepOperation implements Operation {

	private final long time;
	
	public SleepOperation(long time) {
		this.time = time;
	}
	
	public Page execute(HtmlPage page) throws Exception {
		Thread.sleep(time);
		return page;
	}

	public String toString() {
		return "Sleep " + time + "ms";
	}
}
