package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class MatrixDao {

	public static String getImage(String dataset_1, String dataset_2) {
        return (String) HibernateUtil.getCurrentSession()
			.createSQLQuery(
                "SELECT matrix.image " +
                "FROM dataset_dataset_matrix AS matrix " +
                "  JOIN dataset AS d1 ON d1.id = matrix.dataset_1_fk " +
                "  JOIN dataset AS d2 ON d2.id = matrix.dataset_2_fk " +
                "WHERE d1.name = :dataset_1 AND d2.name = :dataset_2"
			)
            .setString("dataset_1", dataset_1)
            .setString("dataset_2", dataset_2)
            .uniqueResult();
    }
}