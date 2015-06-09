package edu.mssm.pharm.maayanlab.Harmonizome.util;

import javax.servlet.http.HttpServletRequest;

public class Query {

	public static String get(HttpServletRequest request) {
		return get(request, false);
	}
	
	public static String get(HttpServletRequest request, boolean decodeUrl) {
		String path = request.getPathInfo();
		if (path.equals("/")) {
			return "";
		} else {
			String query = path.substring(1);
			return decodeUrl ? URLEncoder.decode(query) : query;
		}
	}
	
	public static String get(HttpServletRequest request, String param) {
		String selectedParam = request.getParameter(param);
		return selectedParam == null ? null : URLEncoder.decode(selectedParam);
	}
}