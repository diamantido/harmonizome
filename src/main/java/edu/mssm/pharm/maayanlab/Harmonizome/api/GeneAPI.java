package edu.mssm.pharm.maayanlab.Harmonizome.api;

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
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.API_URL + "/gene/*" })
public class GeneAPI extends HttpServlet {

	private static final long serialVersionUID = -5484736863604374714L;

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request);
		if (query == null) {
			List<String> genes = null;//new ArrayList<String>();
			try {
				HibernateUtil.beginTransaction();
				genes = GeneDAO.getSymbols();
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
			PrintWriter out = response.getWriter();
			out.write(gson.toJson(genes));
			out.flush();
		} else {
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

			PrintWriter out = response.getWriter();
			out.write(gson.toJson(gene, Gene.class));
			out.flush();
		}
	}
}
