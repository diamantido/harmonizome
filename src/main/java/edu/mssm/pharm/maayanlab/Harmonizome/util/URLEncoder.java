package edu.mssm.pharm.maayanlab.Harmonizome.util;

public class URLEncoder {

	public static String encode(String text) {
		return text.replace(" ", "_");
	}
	
	public static String decode(String url) {
		return url.replace("_", " ");
	}
}