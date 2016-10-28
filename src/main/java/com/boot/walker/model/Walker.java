package com.boot.walker.model;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.boot.walker.modules.operation.Operation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Walker implements Comparable<Walker> {

	private final Route road;
	private Thread thread;
	private int index = 0;
	private final Long id;
	private long totalTime = 0;
	private boolean showLog = false;
	
	public Walker(Long id, Route road) {
		this.road = road;
		this.id = id;
	}

	public void setShowLog(boolean showLog) {
		this.showLog = showLog;
	}

	public Walker go() {
		thread = new Thread(() -> {
			try {
				walk();
				if (showLog) {
					System.out.println("Total time: " + totalTime);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		});
		thread.start();
		return this;
	}
	
	public void join() throws InterruptedException {
		this.thread.join();
	}

	private void walk() throws Exception {
		if (index == road.getRoute().size()) {
			return;
		}
		Operation operation = road.getRoute().get(index++);
		long time = System.currentTimeMillis();
		Page page = operation.execute((HtmlPage) road.getPage());
		time = System.currentTimeMillis() - time;
		totalTime += time;
		this.log(time, operation.toString());
		road.setPage(page);
		walk();
	}
	
	private void log(long time, String operation) {
		if (this.showLog) {
			System.out.println(toString() + " " + operation + " \tTime:" + time + "ms");
		}
	}

	public String toString() {
		return "Walker:" + id;
	}

	public int compareTo(Walker o) {
		return new CompareToBuilder().append(o.id, this.id).toComparison();
	}
}
