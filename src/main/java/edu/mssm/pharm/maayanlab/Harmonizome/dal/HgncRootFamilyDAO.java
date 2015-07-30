package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class HgncRootFamilyDAO {

	public static HgncRootFamily getByName(String name) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(HgncRootFamily.class).add(Restrictions.eq("name", name).ignoreCase());
		return (HgncRootFamily) criteria.uniqueResult();
	}
}
