package com.boot.walker.modules;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang3.StringUtils;

import com.boot.walker.modules.operation.Operation;
import com.boot.walker.modules.operation.Selector;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Download file found in SRC.
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 * @since Set 26, 2016
 */
public class DownloadOperation implements Operation {

	private final String directorySaveFile;
	private final Selector selector;
	private File newFile;

	public DownloadOperation(String directorySaveFile, Selector selector) {
		this.directorySaveFile = directorySaveFile;
		this.selector = selector;
	}

	public Page execute(HtmlPage page) throws Exception {
		String link;
		try {
			link = this.selector.search(page);
		} catch (ElementNotFoundException e) {
			throw new Exception("Not found " + e.getElementName());
		}
		if (StringUtils.isEmpty(link)) {
			throw new Exception("Not found content of the selector!");
		}

		String nameFile = StringUtils.substringAfterLast(link, "/");
		this.newFile = new File(directorySaveFile + File.separatorChar + nameFile);

		URL url = page.getFullyQualifiedUrl(link);

		Files.copy(url.openStream(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		return page;
	}

	public File getFile() {
		return newFile;
	}

}
