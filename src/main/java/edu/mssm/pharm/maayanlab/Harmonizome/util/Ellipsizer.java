package edu.mssm.pharm.maayanlab.Harmonizome.util;

/* Credit: http://stackoverflow.com/a/3657496/1830334.
 */
public class Ellipsizer {

	private final static String NON_THIN = "[^iIl1\\.,']";

	private static int textWidth(String str) {
	    return (int) (str.length() - str.replaceAll(NON_THIN, "").length() / 2);
	}

	public static String trim(String text, int max) {
		if (text == null) {
			return "";
		}

	    if (textWidth(text) <= max)
	        return text;

	    // Start by chopping off at the word before max
	    // This is an over-approximation due to thin-characters...
	    int end = text.lastIndexOf(' ', max - 3);

	    // Just one long word. Chop it off.
	    if (end == -1)
	        return text.substring(0, max-3) + "...";

	    // Step forward as long as textWidth allows.
	    int newEnd = end;
	    do {
	        end = newEnd;
	        newEnd = text.indexOf(' ', end + 1);

	        // No more spaces.
	        if (newEnd == -1)
	            newEnd = text.length();

	    } while (textWidth(text.substring(0, newEnd) + "...") < max);

	    return text.substring(0, end) + "...";
	}
}
