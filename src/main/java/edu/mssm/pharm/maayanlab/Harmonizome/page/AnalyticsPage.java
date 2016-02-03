package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Download;
import edu.mssm.pharm.maayanlab.Harmonizome.model.UserSearch;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;


@WebServlet(urlPatterns = {"/" + Constant.ADMIN_URL + "/analytics"})
public class AnalyticsPage extends HttpServlet {

	private static final long serialVersionUID = 8200729575336567137L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HibernateUtil.beginTransaction();
            List<UserSearch> searchedWords = GenericDao.getAll(UserSearch.class);
            List<Download> downloads = GenericDao.getAll(Download.class);
            request.setAttribute("searchedWords", searchedWords);
            request.setAttribute("downloads", downloads);
            request.getRequestDispatcher(Constant.TEMPLATE_DIR + "analytics.jsp").forward(request, response);
            HibernateUtil.commitTransaction();
        } catch (HibernateException he) {
            he.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.close();
        }
    }
}