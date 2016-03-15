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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.HeatMapDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;


@WebServlet(urlPatterns = {"/" + Constant.HEAT_MAPS_URL + "/*"})
public class HeatMapPages extends HttpServlet {

	private static final long serialVersionUID = 7943552797137808480L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = UrlUtil.getPath(request);
        try {
            HibernateUtil.beginTransaction();
            if (type.equals("dataset_pairs")) {
                doDatasetPairsPage(request, response);
            } else if (type.equals("input_genes")){
            	doDatasetWithInputGenes(request, response, type);
            } else {
                doDatasetPage(request, response, type);
            }
            HibernateUtil.commitTransaction();
        } catch (HibernateException he) {
            he.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.close();
        }
    }

    public void doDatasetPage(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException {
        List<Dataset> datasets = HeatMapDao.getDatasetsFromType(type);
        String typeView;
        typeView = StringUtils.capitalize(type);
        request.setAttribute("datasets", datasets);
        request.setAttribute("type", type);
        request.setAttribute("typeView", typeView);
        request.getRequestDispatcher(Constant.TEMPLATE_DIR + "datasetHeatMaps.jsp").forward(request, response);
    }
    
    public void doDatasetWithInputGenes(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException {
        List<Dataset> datasets = GenericDao.getAll(Dataset.class);
        String typeView;
        typeView = StringUtils.capitalize(type);
        request.setAttribute("datasets", datasets);
        request.setAttribute("type", type);
        request.setAttribute("typeView", typeView);
        request.getRequestDispatcher(Constant.TEMPLATE_DIR + "datasetHeatMapWithInputGenes.jsp").forward(request, response);
    }
    
    public void doDatasetPairsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> filtered = HeatMapDao.getLeftDatasetInPair();
        request.setAttribute("datasets", filtered);
        request.getRequestDispatcher(Constant.TEMPLATE_DIR + "datasetPairHeatMaps.jsp").forward(request, response);
    }
}