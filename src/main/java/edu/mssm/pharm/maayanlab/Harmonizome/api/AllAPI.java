package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.API_BASE_URL + "/all/*" })
public class AllAPI extends HttpServlet {

	private static final long serialVersionUID = 778955897675398125L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String dataset = request.getParameter("dataset");
		String datasetGroup = request.getParameter("datasetGroup");
		String datasetType = request.getParameter("datasetType");
		String attribute = request.getParameter("attribute");
		String attributeGroup = request.getParameter("attributeGroup");
		String attributeType = request.getParameter("attributeType");
		String gene = request.getParameter("gene");
		String geneFamily = request.getParameter("geneFamily");

		List<Dataset> datasetResults = null;
		try {
			HibernateUtil.beginTransaction();
			datasetResults = DAO.getDatasetsByFilters(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, geneFamily);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
		
		System.out.println("------------");
		for (Dataset ds : datasetResults) {
			System.out.println(ds.getName());
		}
	}
}