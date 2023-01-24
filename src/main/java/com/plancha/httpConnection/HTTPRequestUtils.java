package com.plancha.httpConnection;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HTTPRequestUtils {

	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (entry != null) {
					result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
					result.append("=");
					result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
					result.append("&");
				}else {
					System.out.println("Entry null");
				}
			}
		}
		String resultString = "?" + result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}
}
