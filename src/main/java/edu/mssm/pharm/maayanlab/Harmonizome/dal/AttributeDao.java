package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class AttributeDao {

	public static Attribute getAll(String query) {
		return (Attribute) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attribute FROM Attribute AS attribute " +
				"WHERE attribute.nameFromNamingAuthority = :query"
			)
			.setString("query",  query)
			.list();
	}
}
