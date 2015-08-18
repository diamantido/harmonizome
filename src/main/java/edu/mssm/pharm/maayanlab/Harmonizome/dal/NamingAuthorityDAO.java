package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class NamingAuthorityDAO {

	public static NamingAuthority getFromName(String name) {
		return (NamingAuthority) HibernateUtil.getCurrentSession()
			.createCriteria(NamingAuthority.class)
			.add(Restrictions.eq("name", name).ignoreCase())
			.uniqueResult();
	}
}