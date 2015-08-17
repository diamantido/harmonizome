package edu.mssm.pharm.maayanlab.Harmonizome.net;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

	public static String getPath(HttpServletRequest request) throws UnsupportedEncodingException {
		String path = request.getPathInfo();
		if (pathIsNull(path)) {
			return null;
		} else {
			// Remove the leading slash.
			String query = path.substring(1);
			return UrlCodec.decode(query);
		}
	}

	public static String[] getPathAsArray(HttpServletRequest request, boolean decodeUrl) throws UnsupportedEncodingException {
		String pathStr = request.getPathInfo();
		if (pathIsNull(pathStr)) {
			return null;
		} else {
			String[] temp = pathStr.split("/");
			// The path has a leading forward slash, so the first element in
			// the array is empty. Remove it.
			String[] result = Arrays.copyOfRange(temp, 1, temp.length);
			for (int i = 0; i < result.length; i++) {
				result[i] = UrlCodec.decode(result[i]);
			}
			return result;
		}
	}

	public static String getParameter(HttpServletRequest request, String param) throws UnsupportedEncodingException {
		String selectedParam = request.getParameter(param);
		return selectedParam == null ? null : UrlCodec.decode(selectedParam);
	}
	
	private static boolean pathIsNull(String path) {
		return path == null || path.equals("/") || path.equals("");
	}
}