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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AssociationDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneSetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + GeneSet.ENDPOINT, "/" + GeneSet.ENDPOINT + "/*" })
public class GeneSetPage extends HttpServlet {

	private static final long serialVersionUID = 6236670940063554419L;

	public void doGetTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response, true);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response, false);
	}

    private void doGet(HttpServletRequest request, HttpServletResponse response, boolean isTest) throws ServletException, IOException {
		String[] query = UrlUtil.getPathAsArray(request);
		if (query.length == 2) {
			try {
				HibernateUtil.beginTransaction();
				String name = query[0];
				String datasetName = query[1];
				GeneSet geneSet = GeneSetDao.getFromNameAndDataset(name, datasetName);
				if (geneSet == null) {
					request.setAttribute("query", query[0] + "/" + query[1]);
					request.getRequestDispatcher(Constant.TEMPLATE_DIR + "noSearchResults.jsp").forward(request, response);
				} else {
					Pair<List<Association>, List<Association>> genesByAssociation = AssociationDao.getFromGeneSetByValue(name, datasetName);
					if (!isTest) {
						geneSet.setNumPageViews(geneSet.getNumPageViews() + 1);
					}
					int numGenes = genesByAssociation.getLeft().size() + genesByAssociation.getRight().size();
					String geneSetDescription = geneSet.getDataset().getGeneSetDescription();
					String strongName = "<strong>" + geneSet.getNameFromDataset() + "</strong>";
					geneSetDescription = geneSetDescription.replace("{0}", strongName);
					geneSetDescription = numGenes + " " + geneSetDescription;
					request.setAttribute("geneSetDescription", geneSetDescription);
					request.setAttribute("geneSet", geneSet);
					request.setAttribute("genesByAttribute", genesByAssociation);
					request.getRequestDispatcher(Constant.TEMPLATE_DIR + "geneSet.jsp").forward(request, response);
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				he.printStackTrace();
				HibernateUtil.rollbackTransaction();
			} finally {
				HibernateUtil.close();
			}
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}