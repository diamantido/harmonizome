package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

public class URLUtil {

	public static String get(HttpServletRequest request) throws UnsupportedEncodingException {
		return get(request, false);
	}

	public static String get(HttpServletRequest request, boolean decodeUrl) throws UnsupportedEncodingException {
		String path = request.getPathInfo();
		if (path == null || path.equals("/") || path.equals("")) {
			return null;
		} else {
			String query = path.substring(1);
			return decodeUrl ? URLDecoder.decode(query, "UTF-8") : query;
		}
	}
	
	public static String get(HttpServletRequest request, String param) throws UnsupportedEncodingException {
		String selectedParam = request.getParameter(param);
		return selectedParam == null ? null : URLDecoder.decode(selectedParam, "UTF-8");
	}
}