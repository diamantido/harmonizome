package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Gene.ENDPOINT + "/*" })
public class GeneAPI extends HttpServlet {

	private static final long serialVersionUID = -5484736863604374714L;

	private static Gson gson;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request);
		if (query == null) {
			doGetAll(request, response);
		} else {
			doGetBySymbol(request, response, query);
		}	
	}
	
	public void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cursor = request.getParameter(Constant.API_CURSOR);
		int startAt = cursor == null ? 0 : Integer.parseInt(cursor);
		GeneDAO geneDAO = new GeneDAO();
		
		Map<String, Object> geneSchema = new HashMap<String, Object>();
		List<Gene> genes = null;
		String query = request.getParameter("q");
		try {
			HibernateUtil.beginTransaction();
			genes = geneDAO.getAll(query, startAt);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		int next = startAt + Constant.API_MAX_RESULTS;
		String nextString = "/" + Constant.API_URL + "/" + Gene.ENDPOINT + "?" + Constant.API_CURSOR + "=" + next;
		geneSchema.put("next", nextString);
		geneSchema.put("genes", genes);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneInfoSerializer());
		gson = gsonBuilder.create();
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(geneSchema));
		out.flush();
	}
	
	public void doGetBySymbol(HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSerializer());
		gson = gsonBuilder.create();
		Gene gene = null;
		try {
			HibernateUtil.beginTransaction();
			gene = GeneDAO.getBySymbol(query);
			if (gene == null) {
				gene = GeneDAO.getBySynonymSymbol(query);
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
		out.write(gson.toJson(gene, Gene.class));
		out.flush();
	}
}
