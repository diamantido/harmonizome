package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;


@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/stats" })
public class StatsAPI extends HttpServlet {
	
	private static final long serialVersionUID = 2755067155579627424L;

	private static final Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateUtil.beginTransaction();
			
			Map<String, Map<String, Long>> stats = new HashMap<String, Map<String, Long>>();
			
			Map<String, Long> attributeCountsPerDataset = new HashMap<String, Long>();
			for (Resource resource : GenericDao.getAll(Resource.class)) {
				attributeCountsPerDataset.put(resource.getName(), resource.getNumAttributes());
			}
			stats.put("attributesPerDataset", attributeCountsPerDataset);
			
			Map<String, Long> datasetsPerDatasetGroup = new HashMap<String, Long>();
			for (DatasetGroup datasetGroup : GenericDao.getAll(DatasetGroup.class)) {
				datasetsPerDatasetGroup.put(datasetGroup.getName(), datasetGroup.getNumDatasets());
			}
			stats.put("datasetsPerDatasetGroup", datasetsPerDatasetGroup);
			
			Map<String, Long> attributesPerAttributeGroup = new HashMap<String, Long>();
			for (AttributeGroup attributeGroup : GenericDao.getAll(AttributeGroup.class)) {
				attributesPerAttributeGroup.put(attributeGroup.getName(), attributeGroup.getNumAttributes());
			}
			stats.put("attributesPerAttributeGroup", attributesPerAttributeGroup);

			String json = gson.toJson(stats);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.flush();
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}
}