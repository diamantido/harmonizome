package edu.mssm.pharm.maayanlab.Harmonizome.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneSetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.StatsDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.*;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = { "/" + Constant.ADMIN_URL + "/refresh_stats" })
public class RefreshStats extends HttpServlet {

	private static final long serialVersionUID = 4359405827672550544L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        PrintWriter out = response.getWriter();
        Map<String, String> message = new HashMap<String, String>();

		try {
			HibernateUtil.beginTransaction();
			
			Long numDatasets = GenericDao.getCount(Dataset.class);
			Long numGenes = GenericDao.getCount(Gene.class);
			Long numAttributes = GenericDao.getCount(Attribute.class);
			Long numResources = GenericDao.getCount(Resource.class);
			Long numFeatures = GenericDao.getCount(Association.class);
			
			Stats stats = StatsDao.get();
			stats.setNumDatasets(numDatasets);
			stats.setNumGenes(numGenes);
			stats.setNumAttributes(numAttributes);
			stats.setNumResources(numResources);
			stats.setNumFeatures(numFeatures);
            message.put("basic stats", "success");

            for (Resource resource : GenericDao.getAll(Resource.class)) {
                long numDatasetsPerResource = DatasetDao.getCountByResource(resource.getName());
                long numGeneSetsPerResource = GeneSetDao.getCountByResource(resource.getName());
                resource.setNumDatasets(numDatasetsPerResource);
                resource.setNumGeneSets(numGeneSetsPerResource);
            }
            message.put("resources", "success");

            for (DatasetGroup datasetGroup : GenericDao.getAll(DatasetGroup.class)) {
                Long count = GeneSetDao.getCountByDatasetGroup(datasetGroup.getName());
                datasetGroup.setNumDatasets(count);
            }
            message.put("dataset groups", "success");

            for (AttributeGroup attributeGroup : GenericDao.getAll(AttributeGroup.class)) {
                Long count = GeneSetDao.getCountByAttributeGroup(attributeGroup.getName());
                attributeGroup.setNumAttributes(count);
            }
            message.put("attribute groups", "success");

            String json = gson.toJson(message);
            out.write(json);

			HibernateUtil.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();

            message.put("message", "unknown error.");
            String json = gson.toJson(message);
            out.write(json);
		} finally {
			HibernateUtil.close();
		}

        out.flush();
	}
}