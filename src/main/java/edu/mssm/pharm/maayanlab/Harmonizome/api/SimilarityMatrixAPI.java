package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.SimilarityMatrix;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.API_BASE_URL + "/similarity_matrix/*" })
public class SimilarityMatrixAPI extends HttpServlet {

	private static final long serialVersionUID = 6293232009862393289L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String geneSymbol = request.getParameter("gene");
		String datasetName = request.getParameter("dataset");
		List<Gene> genesInDataset = null;
		Gene gene = null;

		if (geneSymbol != null) {
			try {
				HibernateUtil.beginTransaction();
				gene = GeneDAO.getBySymbol(geneSymbol);
				genesInDataset = GeneDAO.getFromDataset(datasetName);
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
			SimilarityMatrix.computeGeneSimilarityMatrix(gene, genesInDataset);
		}
	}
}