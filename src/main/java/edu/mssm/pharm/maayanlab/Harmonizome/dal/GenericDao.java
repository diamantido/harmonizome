package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import edu.mssm.pharm.maayanlab.Harmonizome.model.BioEntityMetadata;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import javax.persistence.Table;
import java.util.Iterator;
import java.util.List;

public class GenericDao {
	
	@SuppressWarnings("unchecked")
	public static <E> E get(Class<E> klass, String value) {
		String table = getTableFromClass(klass);
		String field = getKeyColumnFromClass(klass);
		String sql = String.format("SELECT * FROM %s WHERE %s = :value", table, field);
		return (E) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.setString("value", value)
			.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E get(Class<E> klass, Integer id) {
		String table = getTableFromClass(klass);
		String sql = String.format("SELECT * FROM %s WHERE id = :id", table);
		return (E) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.setInteger("id", id)
			.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getAll(Class<E> klass) {
		return (List<E>) HibernateUtil.getCurrentSession()
			.createCriteria(klass)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static <E> List<E> getAll(Class<E> klass, Integer startAt) {
		if (startAt == null) {
			startAt = 0;
		}
		return (List<E>) HibernateUtil.getCurrentSession()
			.createCriteria(klass)
			.setFirstResult(startAt)
			.setMaxResults(Constant.API_MAX_RESULTS)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static <E> List<String> getSuggestions(Class<E> klass, String query) {
		String table = getTableFromClass(klass);
		String field = getKeyColumnFromClass(klass);
		String sql = String.format(
			"SELECT DISTINCT %s FROM %s WHERE %s SOUNDS LIKE :query",
			field, table, field
		);		
		return (List<String>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.setString("query", query)
            .setMaxResults(Constant.DB_MAX_RESULTS)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static <E> List<String> getFromPrefix(Class<E> klass, String query) {
		String table = getTableFromClass(klass);
		String field = getKeyColumnFromClass(klass);
		String sql = String.format(
			"SELECT DISTINCT %s FROM %s WHERE %s LIKE :query",
			field, table, field
		);
		return (List<String>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.setString("query", query + "%")
            .setMaxResults(Constant.DB_MAX_RESULTS)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static <E> List<E> getFromSubstring(Class<E> klass, String query) {
		String table = getTableFromClass(klass);
		String field = getFieldFromClass(klass);
		String sql = String.format(
			"SELECT DISTINCT * FROM %s WHERE MATCH(%s) AGAINST(:query IN BOOLEAN MODE)",
			table, field, query
		);		
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.setString("query", query + "*")
            .setMaxResults(Constant.DB_MAX_RESULTS)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getFromSubstringButIgnoreId(Class<E> klass, String query, int id) {
		String table = getTableFromClass(klass);
		String field = getFieldFromClass(klass);
		String sql = String.format(
			"SELECT DISTINCT * FROM %s WHERE MATCH(%s) AGAINST(:query IN BOOLEAN MODE) AND id != %s",
			table, field, id
		);		
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.setString("query", "*" + query + "*")
            .setMaxResults(Constant.DB_MAX_RESULTS)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static <E> List<E> getFromSubstringInFieldButIgnoreIds(Class<E> klass, String query, List<Integer> ids) {
		String table = getTableFromClass(klass);
		String field = getFieldFromClass(klass);

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
		String sql = String.format(
			"SELECT DISTINCT * FROM %s WHERE MATCH(%s) AGAINST(:query IN BOOLEAN MODE) AND id NOT IN %s",
			table, field, builder.toString()
		);

		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.setString("query", "*" + query + "*")
            .setMaxResults(Constant.DB_MAX_RESULTS)
			.list();
	}
	
	public static <E> Long getCount(Class<E> klass) {
		Criteria criteria = HibernateUtil
			.getCurrentSession()
			.createCriteria(klass);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	public static <E> String getTableFromClass(Class<E> klass) {
		return klass.getAnnotation(Table.class).name();
	}
	
	public static <E> String getFieldFromClass(Class<E> klass) {
		return klass.getAnnotation(BioEntityMetadata.class).keyColumn();
	}
	
	public static <E> String getKeyColumnFromClass(Class<E> klass) {
		BioEntityMetadata anno = klass.getAnnotation(BioEntityMetadata.class);
		return anno.keyColumn();
	}

	@SuppressWarnings("unchecked")
	public static <E> List<String> getFromWordInField(Class<E> klass, String query) {
		String table = getTableFromClass(klass);
		String field = getKeyColumnFromClass(klass);
		String sql = String.format(
			"SELECT DISTINCT %s FROM %s WHERE MATCH(%s) AGAINST(:query IN BOOLEAN MODE);",
			field, table, field
		);
		return (List<String>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.setString("query", "*" + query + "*")
			.list();
	}
}
