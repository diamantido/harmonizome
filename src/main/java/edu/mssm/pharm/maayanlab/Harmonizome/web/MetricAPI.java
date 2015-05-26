package edu.mssm.pharm.maayanlab.Harmonizome.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Metric;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import edu.mssm.pharm.maayanlab.util.Constants;
import edu.mssm.pharm.maayanlab.util.DAO;

@WebServlet(urlPatterns = { Constants.API_BASE_URL + "/metric/*" })
public class MetricAPI extends HttpServlet {

	private static final long serialVersionUID = 1537065814291707577L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getPathInfo();
		String resource = path == null ? null : path.substring(1);
		if (resource == null) {
			doGetAll(resp);
		} else {
			doGetByResource(resp, resource);
		}
	}

	private void doGetByResource(HttpServletResponse resp, String resource) throws IOException {
		Metric metric = null;
		try {
			HibernateUtil.beginTransaction();
			metric = DAO.getMetricByResource(resource);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}

		PrintWriter out = resp.getWriter();
		if (metric == null) {
			out.write("{}");
		} else {
			Map<String, Map<String, Integer>> result = new HashMap<String, Map<String, Integer>>();
			result.put(metric.getResource(), metric.getCounts());
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(result);
			out.write(json);
		}
		out.flush();
	}

	private void doGetAll(HttpServletResponse resp) throws IOException {
		List<Metric> metrics = null;
		try {
			HibernateUtil.beginTransaction();
			metrics = DAO.getAllMetrics();
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}

		Map<String, Map<String, Integer>> result = new HashMap<String, Map<String, Integer>>();
		for (Metric metric : metrics) {
			result.put(metric.getResource(), metric.getCounts());
		}

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(result);

		PrintWriter out = resp.getWriter();
		out.write(json);
		out.flush();
	}
}
