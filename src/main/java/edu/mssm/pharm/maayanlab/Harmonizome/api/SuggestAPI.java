package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Constant.SUGGEST_URL + "/*" })
public class SuggestAPI extends HttpServlet {

	private static final long serialVersionUID = 778955897675398125L;

	private static final Gson gson;

	private static final int MAX_ATTRIBUTES_TO_SUGGEST = 100;
	
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request);
		List<String> suggestions = new ArrayList<String>();
		PrintWriter out = response.getWriter();
		String json = "";
		GeneDAO geneDAO = new GeneDAO();
		
		try {
			HibernateUtil.beginTransaction();
			suggestions.addAll(geneDAO.getByPrefix(query));
			suggestions.addAll(DatasetDAO.getByPrefix(query));
			
			// There are a lot of attributes. Don't suggest everything.
			List<String> attributeSuggestions = AttributeDAO.getByPrefix(query);
			if (attributeSuggestions.size() > MAX_ATTRIBUTES_TO_SUGGEST) {
				attributeSuggestions = attributeSuggestions.subList(0, MAX_ATTRIBUTES_TO_SUGGEST);
			}
			suggestions.addAll(attributeSuggestions);
			
			json = gson.toJson(suggestions);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		out.write(json);
		out.flush();
	}
}