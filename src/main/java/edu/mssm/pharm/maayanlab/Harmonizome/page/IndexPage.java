package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "" })
public class IndexPage extends HttpServlet {

	private static final long serialVersionUID = 6276133214992931402L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> genes = null;
		Long numberOfDatasets = null;
		try {
			HibernateUtil.beginTransaction();
			genes = GeneDAO.getSymbols();
			numberOfDatasets = DatasetDAO.getCount();
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		request.setAttribute("genes", genes);
		request.setAttribute("numberOfDatasets", numberOfDatasets);
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "index.jsp").forward(request, response);
	}
}