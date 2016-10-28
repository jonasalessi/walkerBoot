package com.boot.walker.modules;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.boot.walker.model.Route;
import com.boot.walker.model.Walker;
import com.boot.walker.modules.operation.QuerySelector;

/**
 * Test operation download
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 * @since Set 26, 2016
 */
public class DownloadOperationTest {

	@Test
	public void mustDownloadFile() throws Exception {
		String tempDir = System.getProperty("java.io.tmpdir");
		
		QuerySelector querySelector = new QuerySelector("div[class='post']>p>img");
		
		DownloadOperation download = new DownloadOperation(tempDir, querySelector);
		PathOperation goTo = new PathOperation("http://recursive-labs.com/linuxcareers2016/");
		
		Route route = new Route(false, "http://recursive-labs.com");
		route.add(goTo, download);

		Walker walker = new Walker(1l, route);
		walker.go().join();
		
		assertNotNull(download.getFile());
	}
}
