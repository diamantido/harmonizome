package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class PredictionDao {
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getAllByProbability(Class<E> klass) {
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createCriteria(klass)
			.add(Restrictions.ge("probability", 0.5))
			.addOrder(Order.desc("probability"))

// Removed. This is nice if we want a hard cutoff on the returned results.
//			.setFirstResult(0)
//			.setMaxResults(Constant.ML_PREDICTIONS_MAX_RESULTS)

			.list();
	}
}