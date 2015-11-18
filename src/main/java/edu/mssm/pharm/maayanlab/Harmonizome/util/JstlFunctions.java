package edu.mssm.pharm.maayanlab.Harmonizome.util;

import org.apache.commons.lang3.StringUtils;

public class JstlFunctions {

    public static String highlightSearchTerm(String text, String term) {
        term = term.toLowerCase();
        String capsTerm = StringUtils.capitalize(term);

        if (text.contains(term)) {
            text = text.replace(term, "<strong>" + term + "</strong>");
        }
        if (text.contains(capsTerm)) {
            text = text.replace(capsTerm, "<strong>" + capsTerm + "</strong>");
        }
        return text;
    }
}
