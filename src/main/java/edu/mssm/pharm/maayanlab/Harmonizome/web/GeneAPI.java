package edu.mssm.pharm.maayanlab.Harmonizome.web;

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

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import edu.mssm.pharm.maayanlab.util.Constants;
import edu.mssm.pharm.maayanlab.util.DAO;

@WebServlet(urlPatterns = { Constants.API_BASE_URL + "/gene" })
public class GeneAPI extends HttpServlet {

	private static final long serialVersionUID = -5484736863604374714L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Gene> genes = null;
		try {
			HibernateUtil.beginTransaction();
			genes = DAO.getGenes();
			HibernateUtil.close();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}

		PrintWriter out = resp.getWriter();
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(genes);
		out.write(json);
		out.flush();
	}
}
