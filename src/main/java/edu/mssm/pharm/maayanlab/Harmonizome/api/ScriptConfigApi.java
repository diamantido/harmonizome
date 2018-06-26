package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ScriptConfigSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DownloadType;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = {
	"/" + Constant.API_DARK_URL + "/script_config",
	"/" + Constant.API_DARK_URL + "/script_config" + "/"
})
public class ScriptConfigApi extends HttpServlet {

	private static final long serialVersionUID = 7240269807104096923L;
	private static Gson gson;
	
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateUtil.beginTransaction();

			Map<String, String> datasets = new HashMap<String, String>();
			for (Dataset dataset : GenericDao.getAll(Dataset.class)) {
				datasets.put(dataset.getName(), dataset.getDirectory());
			}

			List<String> downloads = new ArrayList<String>();
			for (DownloadType downloadType : GenericDao.getAll(DownloadType.class)) {
				downloads.add(downloadType.getFilename());
			}

			PrintWriter out = response.getWriter();
			ScriptConfigSchema schema = new ScriptConfigSchema(datasets, downloads);
			out.write(gson.toJson(schema));
			out.flush();

			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}
}
