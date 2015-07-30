package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/gene", "/gene/*" })
public class GenePage extends HttpServlet {

	private static final long serialVersionUID = 4256183225988457817L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String query = URLUtil.getPath(request, true);
		Gene gene = null;
		boolean isSynonym = false;
		List<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>> attributesByDataset = null;

		try {
			HibernateUtil.beginTransaction();
			if (query != null) {
				gene = GeneDAO.getBySymbol(query);
				if (gene == null) {
					gene = GeneDAO.getBySynonymSymbol(query);
					if (gene != null) {
						isSynonym = true;
					}
				}
				if (gene != null) {
					attributesByDataset = AttributeDAO.getByDatasetsFromGene(query);
				}
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		if (gene == null) {
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.jsp").forward(request, response);
		} else {
			request.setAttribute("note", isSynonym ? "Gene; redirected from " + query : "Gene");
			request.setAttribute("gene", gene);
			request.setAttribute("attributesByDataset", attributesByDataset);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "gene.jsp").forward(request, response);
		}
	}
}
