package edu.mssm.pharm.maayanlab.Harmonizome.net;

import java.io.UnsupportedEncodingException;

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
			System.out.println(query);
			return decodeUrl ? URLCodec.decode(query) : query;
		}
	}
	
	public static String get(HttpServletRequest request, String param) throws UnsupportedEncodingException {
		String selectedParam = request.getParameter(param);
		return selectedParam == null ? null : URLCodec.decode(selectedParam);
	}
}