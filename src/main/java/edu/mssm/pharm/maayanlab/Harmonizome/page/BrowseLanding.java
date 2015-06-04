package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Feature;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.PAGES_BASE_URL + "/browse/*" })
public class BrowseLanding extends HttpServlet {

	private static final long serialVersionUID = 1469559115050948011L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Dataset> datasets = null;
		try {
			HibernateUtil.beginTransaction();
			datasets = DAO.getAllDatasets();
			for (Dataset ds : datasets) {
				Set<Attribute> attributes = new HashSet<Attribute>();
				Set<Gene> genes = new HashSet<Gene>();
				for (Feature f : ds.getFeatures()) {
					attributes.add(f.getAttribute());
					genes.add(f.getGene());
				}
				System.out.println(attributes.size());
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}

		//req.setAttribute("numberOfAttributes", attributes.size());
		//req.setAttribute("numberOfGenes", genes.size());
		req.setAttribute("datasets", datasets);
		req.getRequestDispatcher(Constant.TEMPLATE_DIR + "browse_landing.jsp").forward(req, resp);
	}
}