package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Stats;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class StatsDAO {
	
	public static Stats get() {
		return (Stats) HibernateUtil
			.getCurrentSession()
			.createQuery("SELECT stats FROM Stats AS stats WHERE id IS 1")
			.uniqueResult();
	}
}
