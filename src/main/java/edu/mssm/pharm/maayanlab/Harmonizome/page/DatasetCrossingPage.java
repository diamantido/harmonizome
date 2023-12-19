package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetCrossingDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;


@WebServlet(urlPatterns = {"/cross"})
public class DatasetCrossingPage extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HibernateUtil.beginTransaction();
            doDatasetCrossingPage(request, response);
            HibernateUtil.commitTransaction();
        } catch (HibernateException he) {
            he.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.close();
        }
    }
    public void doDatasetCrossingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> filtered = DatasetCrossingDao.getLeftDatasetInPair();
        request.setAttribute("datasets", filtered);
        request.getRequestDispatcher(Constant.TEMPLATE_DIR + "datasetCrossing.jsp").forward(request, response);
    }
}