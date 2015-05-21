package edu.mssm.pharm.maayanlab.Harmonizome.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.hibernateObjects.DbGene;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import edu.mssm.pharm.maayanlab.util.Constants;
import edu.mssm.pharm.maayanlab.util.DAO;

@WebServlet(urlPatterns = { Constants.API_BASE_URL + "/genes" })
public class Genes extends HttpServlet {

	private static final long serialVersionUID = -5484736863604374714L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		System.out.println(name);

		DbGene dbGene = null;
		try {
			HibernateUtil.beginTransaction();
			dbGene = DAO.getGeneByName(name);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}

		PrintWriter out = resp.getWriter();
		out.write("" + dbGene.getId());
		out.flush();
	}
}
