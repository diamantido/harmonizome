package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import org.hibernate.criterion.Order;

import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class PredictionDao {
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getAllByProbability(Class<E> klass) {
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createCriteria(klass)
			.addOrder(Order.desc("probability"))
			.setFirstResult(0)
			.setMaxResults(Constant.DB_MAX_RESULTS)
			.list();
	}
}