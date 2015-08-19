package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Feature;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class FeatureDAO {

	@SuppressWarnings("unchecked")
	public static List<Feature> getByGeneSet(String datasetNameFromDataset, String datasetName) {
		return (List<Feature>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT feature FROM Feature AS feature " +
				"JOIN feature.gene AS gene " +
				"JOIN feature.attribute AS attribute " +
				"JOIN attribute.dataset AS dataset " +
				"WHERE dataset.name = :datasetName AND attribute.nameFromDataset = :datasetNameFromDataset"
			)
			.setString("datasetNameFromDataset", datasetNameFromDataset)
			.setString("datasetName", datasetName)
			.list();
	}
}
