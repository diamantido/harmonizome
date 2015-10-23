package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import edu.mssm.pharm.maayanlab.Harmonizome.model.UserSearch;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserSearchDao {

    public static void save(String text) {
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery(
            "SELECT query FROM UserSearch AS query " +
            "WHERE query.keywords = :text"
        );
        query.setString("text", text);

        UserSearch search = (UserSearch) query.uniqueResult();
        if (search != null) {
            search.setCount(search.getCount() + 1);
            HibernateUtil.getCurrentSession().merge(search);
        } else {
            UserSearch newSearch = new UserSearch(text);
            HibernateUtil.getCurrentSession().save(newSearch);
        }
    }

    public static List<String> getAll() {
        String sql = String.format("SELECT keywords FROM UserSearch");
        return (List<String>) HibernateUtil.getCurrentSession()
            .createQuery(sql)
            .list();
    }
}