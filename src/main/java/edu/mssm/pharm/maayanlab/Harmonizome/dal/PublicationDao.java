package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Publication;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class PublicationDao {

	@SuppressWarnings("unchecked")
	public static List<Publication> getFromResource(String resourceName) {
		return (List<Publication>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT publication FROM Publication AS publication " +
				"JOIN publication.datasets AS dataset " +
				"JOIN dataset.resource AS resource " +
				"WHERE resource.name = :resourceName"
			)
			.setString("resourceName", resourceName)
			.list();
	}
}