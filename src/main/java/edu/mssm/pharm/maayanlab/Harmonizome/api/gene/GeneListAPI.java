package edu.mssm.pharm.maayanlab.Harmonizome.api.gene;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.GeneBaseSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.GeneInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.GeneSetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Gene.ENDPOINT })
public class GeneListAPI extends HttpServlet {

	private static final long serialVersionUID = -8924091688621192218L;
	
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
		GeneBaseSchema geneBaseSchema = new GeneBaseSchema(startAt);
		
		List<Gene> genes = null;
		String query = request.getParameter("q");
		try {
			HibernateUtil.beginTransaction();
			genes = geneDAO.getAll(query, startAt);
			geneBaseSchema.setGenes(genes);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneInfoSerializer());
		gson = gsonBuilder.create();
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(geneBaseSchema));
		out.flush();
	}
	
	public void doGetBySymbol(HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetInfoSerializer());
		gson = gsonBuilder.create();
		Gene gene = null;
		try {
			HibernateUtil.beginTransaction();
			gene = GeneDAO.getFromSymbol(query);
			if (gene == null) {
				gene = GeneDAO.getFromSynonymSymbol(query);
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
		out.write(gson.toJson(gene, Gene.class));
		out.flush();
	}
}
