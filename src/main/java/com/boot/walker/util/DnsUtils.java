package com.boot.walker.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Utilitary for DNS
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 * @since Oct 05, 2016
 */
public class DnsUtils {

	private static final String SUFFIX = "/";

	public static String addPath(String dns, String path) {
		if (StringUtils.isEmpty(path) || StringUtils.isEmpty(dns)) {
			return dns;
		}
		if (path.startsWith(dns)){
			return path;
		}
		if (path.startsWith(SUFFIX)) {
			path = path.substring(1);
		}
		if (!dns.endsWith(SUFFIX)) {
			dns += SUFFIX;
		}
		return dns + path;
	}

}
