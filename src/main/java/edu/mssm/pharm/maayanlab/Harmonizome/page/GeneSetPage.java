package edu.mssm.pharm.maayanlab.Harmonizome.page;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneSetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.BioEntityAlphabetizer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = { "/" + GeneSet.ENDPOINT, "/" + GeneSet.ENDPOINT + "/*" })
public class GeneSetPage extends HttpServlet {

	private static final long serialVersionUID = 6236670940063554419L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] query = UrlUtil.getPathAsArray(request, true);
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
					Pair<List<Gene>, List<Gene>> genesByAttribute = GeneDao.getFromGeneSetByValue(name, datasetName);
					Collections.sort(genesByAttribute.getRight(), new BioEntityAlphabetizer());
					Collections.sort(genesByAttribute.getLeft(), new BioEntityAlphabetizer());
					int numGenes = genesByAttribute.getLeft().size() + genesByAttribute.getRight().size();
					String geneSetDescription = geneSet.getDataset().getGeneSetDescription();
					String strongName = "<strong>" + geneSet.getNameFromDataset() + "</strong>";
					geneSetDescription = geneSetDescription.replace("{0}", strongName);
					geneSetDescription = numGenes + " " + geneSetDescription;
                    System.out.println(geneSetDescription);
					request.setAttribute("geneSetDescription", geneSetDescription);
					request.setAttribute("geneSet", geneSet);
					request.setAttribute("genesByAttribute", genesByAttribute);
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