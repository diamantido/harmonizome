package edu.mssm.pharm.maayanlab.Harmonizome.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlCodec {

	// Use our own custom encoding/decoding for slashes. We could configure
	// Tomcat to accept "%2F" but any other server between the Harmonizome
	// and the user could strip out slashes for us. This seems like the safest
	// approach.
	private static final String slashReplacement = "$slash$";
	
	private static final String plusReplacement = "$plus$";

	private static final String characterEncoding = "UTF-8";
	
	private static final String geneSetSeparator = "/";

	public static String encode(String url) {
		try {
			url = url.replace("/", slashReplacement).replace("+", plusReplacement);
			return URLEncoder.encode(url, characterEncoding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public static String decode(String url) throws UnsupportedEncodingException {
		String decoded = URLDecoder.decode(url, characterEncoding);
		return decoded.replace(slashReplacement, "/").replace(plusReplacement, "+");
	}
	
	// TODO: Should this be generic? "encodeArray" or something?
	public static String encodeGeneSet(String attributeName, String datasetName) throws UnsupportedEncodingException {
		return encode(attributeName) + geneSetSeparator + encode(datasetName);
	}
	
	public static String decodeGeneSet(String attributeName, String datasetName) throws UnsupportedEncodingException {
		return decode(attributeName) + geneSetSeparator + decode(datasetName);
	}
}