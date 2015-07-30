package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class ProteinDAO {

	public static Protein getByName(String symbol) {
		return (Protein) HibernateUtil
			.getCurrentSession()
			.createCriteria(Protein.class)
			.add(Restrictions.eq("symbol", symbol).ignoreCase())
			.uniqueResult();
	}
}