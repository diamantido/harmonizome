package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class DatasetDatasetMatricesDao {

	@SuppressWarnings("unchecked")
	public static String get(String dataset_1, String dataset_2) {
        return (String) HibernateUtil.getCurrentSession()
			.createSQLQuery(
                "SELECT ddm.image " +
                "FROM dataset_dataset_matrices AS ddm " +
                "  JOIN dataset AS d1 ON d1.id = ddm.dataset_1 " +
                "  JOIN dataset AS d2 ON d2.id = ddm.dataset_2 " +
                "WHERE d1.name = :dataset_1 AND d2.name = :dataset_2"
			)
            .setString("dataset_1", dataset_1)
            .setString("dataset_2", dataset_2)
            .uniqueResult();
	}
}