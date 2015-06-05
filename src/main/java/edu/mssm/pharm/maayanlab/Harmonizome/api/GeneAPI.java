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

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.serializer.GeneSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.API_BASE_URL + "/gene/*" })
public class GeneAPI extends HttpServlet {

	private static final long serialVersionUID = -5484736863604374714L;

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String symbol = req.getParameter("symbol");
		if (symbol == null) {
			List<String> genes = new ArrayList<String>();
			try {
				HibernateUtil.beginTransaction();
				for (Gene g : DAO.getAllGenes()) {
					genes.add(g.getSymbol());
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
			PrintWriter out = resp.getWriter();
			out.write(gson.toJson(genes));
			out.flush();
		} else {
			String json = "";
			Gene gene = null;
			try {
				HibernateUtil.beginTransaction();
				gene = DAO.getGeneBySymbol(symbol);
				if (gene == null) {
					gene = DAO.getGeneBySynonymSymbol(symbol);
				}
				json = gson.toJson(gene, Gene.class);
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
	
			PrintWriter out = resp.getWriter();
			out.write(json);
			out.flush();
		}
	}
}
