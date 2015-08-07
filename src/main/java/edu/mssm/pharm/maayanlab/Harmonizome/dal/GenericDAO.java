package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class GenericDAO {
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getAll(Class<E> klass, Integer startAt) {
		startAt = (startAt == null) ? 0 : startAt;
		return (List<E>) HibernateUtil.getCurrentSession()
			.createCriteria(klass)
			.setFirstResult(startAt)
			.setMaxResults(startAt + Constant.API_MAX_RESULTS)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getAllFromQuery(Class<E> klass, String table, String field, String query, Integer startAt) {
		startAt = (startAt == null) ? 0 : startAt;
		if (query == null) {
			return getAll(klass, startAt);
		}
		String sql = String.format("SELECT * FROM %s WHERE MATCH(%s) AGAINST('%s*' IN BOOLEAN MODE)", table, field, query);
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.setFirstResult(startAt)
			.setMaxResults(startAt + Constant.API_MAX_RESULTS)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getSuggestions(String table, String field, String query) {
		String sql = String.format("SELECT DISTINCT %s FROM %s WHERE %s SOUNDS LIKE '%s'", field, table, field, query);		
		return (List<String>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<String> getByPrefix(String table, String field, String query) {
		String sql = String.format("SELECT DISTINCT %s FROM %s WHERE %s LIKE '%s%s'", field, table, field, query, "%");		
		return (List<String>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static <E> List<E> getBySubstringInField(Class<E> klass, String table, String field, String query) {
		String sql = String.format("SELECT DISTINCT * FROM %s WHERE MATCH(%s) AGAINST('%s*' IN BOOLEAN MODE)", table, field, query);		
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getBySubstringInFieldButIgnoreId(Class<E> klass, String table, String field, String query, int id) {
		String sql = String.format("SELECT DISTINCT * FROM %s WHERE MATCH(%s) AGAINST('%s*' IN BOOLEAN MODE) AND id != %s", table, field, query, id);		
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static <E> List<E> getBySubstringInFieldButIgnoreIds(Class<E> klass, String table, String field, String query, List<Integer> ids) {
		StringBuilder builder  = new StringBuilder();
		Iterator<Integer> iter = ids.iterator();
		builder.append("(");
		while (iter.hasNext()) {
			builder.append(iter.next());
	        if (iter.hasNext()) {
	        	builder.append(",");
	        }
		}
		builder.append(")");
		String sql = String.format("SELECT DISTINCT * FROM %s WHERE MATCH(%s) AGAINST('%s*' IN BOOLEAN MODE) AND id NOT IN %s", table, field, query, builder.toString());		
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.list();
	}
	
	public static <E> Long getCount(Class<E> klass) {
		Criteria criteria = HibernateUtil
			.getCurrentSession()
			.createCriteria(klass);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}
