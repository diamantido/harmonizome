package edu.mssm.pharm.maayanlab.Harmonizome.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import edu.mssm.pharm.maayanlab.Harmonizome.api.*;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.*;
import edu.mssm.pharm.maayanlab.Harmonizome.model.*;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class OpenAiApiController {
    
    private static final String apiKey = System.getenv("OPENAI_API_KEY");
    private static final String harmonizomeApiUrl = Constant.ORIGIN() + "/" + Constant.HARMONIZOME + "/" + Constant.API_URL;
    private static final String systemMessage = "You are Harmonizome AI. Your function is to answer any questions pertaining to information stored in Harmonizome. You will only answer relevant queries. When an irrelevant query is passed, you will offer an alternative that is relevant." +
    " You should call a function to query the Harmonizome API if the user asks for any data. If there is any uncertainty about calling a function or which arguments to pass to a function, please ask the user before continuing." +
    " Never pass an argument unless it is explicitly included by the user. Use the function description to create an introduction to the function's reply." +
    " If a function has been called, it is essential that your only response is introducing the function called. Your response needs to resemble this format:" +
    " 'Please select the dataset you would like to see associations from:' or 'Here are the genes most associated with lung carcinoma from the DISEASES Text-mining Gene-Disease Assocation Evidence Scores dataset.'" +
    " You should never include a dataset, gene, or gene set in your response unless it has been directly passed to you by the user.";

    public static JSONArray createHistory() {
        JSONObject system = new JSONObject();
        system.put("role", "system");
        system.put("content", systemMessage);

        JSONObject intro = new JSONObject();
        intro.put("role", "assistant");
        intro.put("content", "Hello, I am Harmonizome AI, a language model designed to answer questions related to the Harmonizome database. Submit a query, and I will retrieve information from Harmonizome to answer.");
        
        JSONArray history = new JSONArray();
        history.put(system);
        history.put(intro);
        return history;
    }

    public static JSONArray chatGPT(JSONArray history, Boolean functionCalling) throws IOException {
        history = cleanHistory(history);

        try {
            File file = new File("webapps/Harmonizome/json/functions.json");
            JSONArray functions = new JSONArray(new BufferedReader(new FileReader(file)).lines().reduce((a, b) -> a + b).get());

            String gptURL = "https://api.openai.com/v1/chat/completions";
            HttpURLConnection con = (HttpURLConnection) new URL(gptURL).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);

            String callFunction = "none";
            if (functionCalling) {
                callFunction = "auto";
            }

            JSONObject data = new JSONObject();
            data.put("model", "gpt-4");
            data.put("max_tokens", 1000);
            data.put("temperature", 0.1);
            data.put("functions", functions);
            data.put("messages", history);
            data.put("function_call", callFunction);

            con.setDoOutput(true);
            con.getOutputStream().write(data.toString().getBytes());
            
            String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines().reduce((a, b) -> a + b).get();
            JSONObject reply = new JSONObject(output).getJSONArray("choices").getJSONObject(0).getJSONObject("message");

            history.put(reply);

            if (reply.has("function_call")) {
                String function = reply.getJSONObject("function_call").getString("name");
                System.out.println(function);
                
                JSONObject arguments = new JSONObject(reply.getJSONObject("function_call").getString("arguments"));
                System.out.println(arguments);

                if (function.equals("getTopGenes")) {
                    history = getTopGenes(history, arguments);
                } else if (function.equals("getBottomGenes")) {
                    history = getBottomGenes(history, arguments);
                }else if (function.equals("getTopGeneSets")) {
                    history = getTopGeneSets(history, arguments);
                } else if (function.equals("getBottomGeneSets")) {
                    history = getBottomGeneSets(history, arguments);
                } else if (function.equals("getGeneSetsFromAttribute")) {
                    history = getGeneSetsFromAttribute(history, arguments);
                } else if (function.equals("getResources")) {
                    history = getResources(history);
                } else if (function.equals("getResource")) {
                    history = getResource(history, arguments);  
                } else if (function.equals("getDatasets")) {
                    history = getDatasets(history, arguments);
                } else if (function.equals("getDataset")) {
                    history = getDataset(history, arguments);  
                } else if (function.equals("getGene")) {
                    history = getGene(history, arguments);  
                }
            }
            return history;
        } catch (IOException e) {
            e.printStackTrace();
            return history;
        }
    }

    public static JSONArray getTopGeneSets(JSONArray history, JSONObject arguments) throws IOException {
        String geneSymbol = arguments.getString("gene");
        Integer numAssociations = arguments.getInt("numAssociations");
        String apiURL = harmonizomeApiUrl+"/gene/"+geneSymbol+"?showAssociations=true";

        String output = sendHarmonizomeAPIQuery(apiURL);
        JSONArray associations = new JSONObject(output).getJSONArray("associations");

        // Get top 5 associations and add to topGeneSets array
        List<JSONObject> associationsList = new ArrayList<>();
        for (int i = 0; i < associations.length(); i++) {
            JSONObject association = associations.getJSONObject(i);
            associationsList.add(association);
        }
        Collections.sort(associationsList, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                Double standardizedValue1 = getDoubleOrDefault(o1, "standardizedValue", Double.NEGATIVE_INFINITY);
                Double standardizedValue2 = getDoubleOrDefault(o2, "standardizedValue", Double.NEGATIVE_INFINITY);
                return Double.compare(standardizedValue2, standardizedValue1);
        }});
        JSONArray topGeneSets = new JSONArray(associationsList.subList(0, Math.min(associationsList.size(), numAssociations)));

        JSONObject reply = new JSONObject();
        reply.put("role","function");
        reply.put("name", "getTopGeneSets");
        reply.put("content", "Table:"+topGeneSets.toString());

        //history = chatGPT(history, false);
        history.put(reply);
        return history;
    }

    public static JSONArray getBottomGeneSets(JSONArray history, JSONObject arguments) throws IOException {
        String geneSymbol = arguments.getString("gene");
        Integer numAssociations = arguments.getInt("numAssociations");
        String apiURL = harmonizomeApiUrl+"/gene/"+geneSymbol+"?showAssociations=true";

        String output = sendHarmonizomeAPIQuery(apiURL);
        JSONArray associations = new JSONObject(output).getJSONArray("associations");

        // Get top 5 associations and add to bottomGeneSets array
        List<JSONObject> associationsList = new ArrayList<>();
        for (int i = 0; i < associations.length(); i++) {
            JSONObject association = associations.getJSONObject(i);
            associationsList.add(association);
        }
        Collections.sort(associationsList, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                Double standardizedValue1 = getDoubleOrDefault(o1, "standardizedValue", Double.POSITIVE_INFINITY);
                Double standardizedValue2 = getDoubleOrDefault(o2, "standardizedValue", Double.POSITIVE_INFINITY);
                return Double.compare(standardizedValue1, standardizedValue2);
        }});
        JSONArray bottomGeneSets = new JSONArray(associationsList.subList(0, Math.min(associationsList.size(), numAssociations)));

        JSONObject reply = new JSONObject();
        reply.put("role","function");
        reply.put("name", "getTopGeneSets");
        reply.put("content", "Table:"+bottomGeneSets.toString());

        //history = chatGPT(history, false);
        history.put(reply);
        return history;
    }

    public static JSONArray getTopGenes(JSONArray history, JSONObject arguments) throws IOException {
        String geneSet = arguments.getString("geneSet").replace(" ", "+");
        String dataset = arguments.getString("dataset").replace(" ", "+");
        Integer numAssociations = arguments.getInt("numAssociations");
        
        String apiURL = harmonizomeApiUrl+"/gene_set/"+geneSet+"/"+dataset+"?showAssociations=true";
        System.out.println("started function");
        try {
            String output = sendHarmonizomeAPIQuery(apiURL);
            JSONArray associations = new JSONObject(output).getJSONArray("associations");

            // Get top 5 associations and add to topGenes array
            List<JSONObject> associationsList = new ArrayList<>();
            for (int i = 0; i < associations.length(); i++) {
                JSONObject association = associations.getJSONObject(i);
                associationsList.add(association);
            }
            Collections.sort(associationsList, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    Double standardizedValue1 = getDoubleOrDefault(o1, "standardizedValue", Double.NEGATIVE_INFINITY);
                    Double standardizedValue2 = getDoubleOrDefault(o2, "standardizedValue", Double.NEGATIVE_INFINITY);
                    return Double.compare(standardizedValue2, standardizedValue1);
            }});
            JSONArray topGenes = new JSONArray(associationsList.subList(0, Math.min(associationsList.size(), numAssociations)));

            JSONObject reply = new JSONObject();
            reply.put("role","function");
            reply.put("name", "getTopGenes");
            reply.put("content", "Table:"+topGenes.toString());

            //history = chatGPT(history, false);
            history.put(reply);
            return history;
        } catch (IOException e) {
            JSONObject reply = new JSONObject();
            reply.put("role","function");
            reply.put("name", "getTopGenes");
            reply.put("content", "Error: Invalid gene set passed");

            history.put(reply);
            return history;
        } 
    }

    public static JSONArray getBottomGenes(JSONArray history, JSONObject arguments) throws IOException {
        String geneSet = arguments.getString("geneSet").replace(" ", "+");
        String dataset = arguments.getString("dataset").replace(" ", "+");
        Integer numAssociations = arguments.getInt("numAssociations");
        String apiURL = harmonizomeApiUrl+"/gene_set/"+geneSet+"/"+dataset+"?showAssociations=true";

        try {
            String output = sendHarmonizomeAPIQuery(apiURL);
            JSONArray associations = new JSONObject(output).getJSONArray("associations");

            // Get top 5 associations and add to bottomGenes array
            List<JSONObject> associationsList = new ArrayList<>();
            for (int i = 0; i < associations.length(); i++) {
                JSONObject association = associations.getJSONObject(i);
                associationsList.add(association);
            }
            Collections.sort(associationsList, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    Double standardizedValue1 = getDoubleOrDefault(o1, "standardizedValue", Double.POSITIVE_INFINITY);
                    Double standardizedValue2 = getDoubleOrDefault(o2, "standardizedValue", Double.POSITIVE_INFINITY);
                    return Double.compare(standardizedValue1, standardizedValue2);
            }});
            JSONArray bottomGenes = new JSONArray(associationsList.subList(0, Math.min(associationsList.size(), numAssociations)));

            JSONObject reply = new JSONObject();
            reply.put("role","function");
            reply.put("name", "getBottomGenes");
            reply.put("content", "Table:"+bottomGenes.toString());

            //history = chatGPT(history, false);
            history.put(reply);
            return history;
        } catch (IOException e) {
            JSONObject reply = new JSONObject();
            reply.put("role","function");
            reply.put("name", "getBottomGenes");
            reply.put("content", "Error: Invalid gene set passed");

            history.put(reply);
            return history;
        } catch (Exception e) {
            e.printStackTrace();
            return history;
        }
    }

    public static JSONArray getGeneSetsFromAttribute(JSONArray history, JSONObject arguments) throws IOException {
        String attribute = arguments.getString("attribute").replace(" ", "+");
        String apiURL = harmonizomeApiUrl+"/attribute/"+attribute;
        String output = sendHarmonizomeAPIQuery(apiURL);
        JSONArray geneSets = new JSONArray();

        try {
            geneSets = new JSONObject(output).getJSONArray("geneSets");
        } catch (JSONException e) {
            JSONObject error = new JSONObject();
            error.put("Error","No gene sets found.");
            geneSets.put(error);
        }

        JSONObject reply = new JSONObject();
        reply.put("role","function");
        reply.put("name", "getGeneSetsFromAttribute");
        reply.put("content", "Options:"+geneSets.toString());

        history = chatGPT(history, false);
        history.put(reply);
        return history;
    }

    public static JSONArray getResources(JSONArray history) throws IOException {
        String apiURL = harmonizomeApiUrl+"/resource";
        String output = sendHarmonizomeAPIQuery(apiURL);
        JSONArray resources = new JSONArray();
        try {
            resources = new JSONObject(output).getJSONArray("entities");
        } catch (JSONException e) {
            JSONObject error = new JSONObject();
            error.put("Error","No resources");
            resources.put(error);
        }
        JSONObject reply = new JSONObject();
        reply.put("role","function");
        reply.put("name", "getResources");
        reply.put("content", resources.toString());

        history.put(reply);
        history = chatGPT(history, false);
        return history;
    }

    public static JSONArray getResource(JSONArray history, JSONObject arguments) throws IOException {
        String apiURL = harmonizomeApiUrl+"/resource/"+arguments.getString("resource").replace(" ","+");
        String output = sendHarmonizomeAPIQuery(apiURL);
        JSONObject outputJSON = new JSONObject(output);
        JSONArray content = new JSONArray();
        try {
            JSONObject resource = new JSONObject();
            resource.put("name", outputJSON.getString("name"));
            resource.put("acronym", outputJSON.getString("acronym"));
            resource.put("description", outputJSON.getString("description"));
            resource.put("datasets", outputJSON.getJSONArray("datasets"));
            content.put(resource);
        } catch (JSONException e) {
            JSONObject error = new JSONObject();
            error.put("Error","Resource not found");
            content.put(error);
        }
        JSONObject reply = new JSONObject();
        reply.put("role","function");
        reply.put("name", "getResource");
        reply.put("content", content.toString());

        history.put(reply);
        history = chatGPT(history, false);
        return history;
    }

    public static JSONArray getDatasets(JSONArray history, JSONObject arguments) throws IOException {
        String apiURL = harmonizomeApiUrl+"/dataset";
        Integer startIndex = arguments.getInt("startIndex");
        if (startIndex > 0) {
            apiURL = apiURL+"?cursor="+startIndex.toString();
        }
        String output = sendHarmonizomeAPIQuery(apiURL);
        JSONArray datasets = new JSONArray();
        try {
            datasets = new JSONObject(output).getJSONArray("entities");
        } catch (JSONException e) {
            JSONObject error = new JSONObject();
            error.put("Error","No datasets");
            datasets.put(error);
        }
        JSONObject reply = new JSONObject();
        reply.put("role","function");
        reply.put("name", "getDatasets");
        reply.put("content", datasets.toString());

        history.put(reply);
        history = chatGPT(history, false);
        return history;
    }

    public static JSONArray getDataset(JSONArray history, JSONObject arguments) throws IOException {
        String apiURL = harmonizomeApiUrl+"/dataset/"+arguments.getString("dataset").replace(" ","+");
        String output = sendHarmonizomeAPIQuery(apiURL);
        JSONObject outputJSON = new JSONObject(output);
        JSONArray content = new JSONArray();
        try {
            JSONObject dataset = new JSONObject();
            dataset.put("name", outputJSON.getString("name"));
            dataset.put("description", outputJSON.getString("description"));
            dataset.put("association", outputJSON.getString("association"));
            dataset.put("measurement", outputJSON.getString("measurement"));
            dataset.put("attributeType", outputJSON.getString("attributeType"));
            content.put(dataset);
        } catch (JSONException e) {
            JSONObject error = new JSONObject();
            error.put("Error","Dataset not found");
            content.put(error);
        }
        JSONObject reply = new JSONObject();
        reply.put("role","function");
        reply.put("name", "getDataset");
        reply.put("content", content.toString());

        history.put(reply);
        history = chatGPT(history, false);
        return history;
    }

    public static JSONArray getGene(JSONArray history, JSONObject arguments) throws IOException {
        String apiURL = harmonizomeApiUrl+"/gene/"+arguments.getString("symbol");
        String output = sendHarmonizomeAPIQuery(apiURL);
        JSONObject outputJSON = new JSONObject(output);
        JSONArray content = new JSONArray();
        try {
            JSONObject gene = new JSONObject();
            gene.put("symbol", outputJSON.getString("symbol"));
            gene.put("ncbiEntrezGeneId", outputJSON.getInt("ncbiEntrezGeneId"));
            gene.put("name", outputJSON.getString("name"));
            gene.put("description", outputJSON.getString("description"));
            content.put(gene);
        } catch (JSONException e) {
            JSONObject error = new JSONObject();
            error.put("Error","Gene not found");
            content.put(error);
        }
        JSONObject reply = new JSONObject();
        reply.put("role","function");
        reply.put("name", "getDataset");
        reply.put("content", content.toString());

        history.put(reply);
        history = chatGPT(history, false);
        return history;
    }

    private static JSONArray cleanHistory(JSONArray history) {
        Integer systemMessageCount = 0;
        Integer systemMessageIndex = -1;
        for (int i = 0; i<history.length(); i++) {
            JSONObject message = history.getJSONObject(i);
            if (message.has("role") && message.getString("role").equals("system")) {
                systemMessageCount += 1;
                systemMessageIndex = i;
            }
        }
        if (systemMessageCount == 1 && systemMessageIndex == 0 && history.getJSONObject(systemMessageIndex).getString("content").equals(systemMessage)) {
            return history;
        } else {
            return new JSONArray();
        }
    }

    private static String sendHarmonizomeAPIQuery(String URL) throws IOException {
        System.out.println(URL);
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();
            con.setRequestMethod("GET");
            
            String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines().reduce((a, b) -> a + b).get();
            return output;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Double getDoubleOrDefault(JSONObject jsonObject, String key, Double defaultValue) {
        try {
            return jsonObject.getDouble(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }
}
