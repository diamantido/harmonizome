package edu.mssm.pharm.maayanlab.Harmonizome.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class JstlFunctions {

    public static String highlightSearchTerm(String text, String fullTerm) {
        fullTerm = fullTerm.toLowerCase();
        ArrayList<String> terms = new ArrayList<String>(Arrays.asList(fullTerm.split(" ")));
        for (String t : terms) {
            text = highlightSearchTerms(text, t);
        }
        return text;
    }

    private static String highlightSearchTerms(String text, String term) {
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

    public static String convertVizTypeToPlainText(String vizType) {
        String[] parts = vizType.split("_");
        String results = "";
        for (int i = 0; i < parts.length; i++) {
            results += StringUtils.capitalize(parts[i]) + " ";
        }
        return results;
    }
}
