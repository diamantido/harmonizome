package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.API_BASE_URL + "/similarity_matrix/*" })
public class SimilarityMatrixAPI extends HttpServlet {

	private static final long serialVersionUID = 6293232009862393289L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String geneSymbol = URLUtil.getParameter(request, "gene");
		String datasetName = URLUtil.getParameter(request, "dataset");
		System.out.println(datasetName);
		Map<String, Set<Attribute>> attributesByGene = null;
		Gene baseGene = null;

		if (geneSymbol != null) {
			try {
				HibernateUtil.beginTransaction();
				baseGene = GeneDAO.getBySymbol(geneSymbol);
				attributesByGene = AttributeDAO.getByGeneFromDataset(datasetName);
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}

			/*System.out.println(attributesByGene.size());
			for (Map.Entry<String, Set<Attribute>> entry : attributesByGene.entrySet()) {
				String g = (String) entry.getKey();
				Set<Attribute> attrs = (Set<Attribute>) entry.getValue();
				System.out.println(g);
				for (Attribute a : attrs) {
					System.out.println(a.getName());
				}
			}*/
			//SimilarityMatrix.computeGeneSimilarityMatrix(baseGene, attributesByGene);
		}
	}
}