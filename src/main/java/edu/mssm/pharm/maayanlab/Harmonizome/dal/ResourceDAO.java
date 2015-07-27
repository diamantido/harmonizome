package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class ResourceDAO {

	public static Resource getByName(String name) {
		Criteria criteria = HibernateUtil.getCurrentSession()
			.createCriteria(Resource.class)
			.add(Restrictions.eq("name", name).ignoreCase());
		return (Resource) criteria.uniqueResult();
	}
}
