package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneSetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Gene.ENDPOINT + "/*" })
public class GeneApi extends HttpServlet {

	private static final long serialVersionUID = -5484736863604374714L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetInfoSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String symbol = UrlUtil.getPath(request);
		Gene gene = null;

		try {
			HibernateUtil.beginTransaction();
			gene = GeneDAO.getFromSymbol(symbol);
			if (gene == null) {
				gene = GeneDAO.getFromSynonymSymbol(symbol);
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
		
		PrintWriter out = response.getWriter();
		if (gene == null) {
			out.write(gson.toJson(new ErrorSchema()));
		} else {
			out.write(gson.toJson(gene, Gene.class));
		}
		out.flush();
	}
}