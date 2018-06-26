package edu.mssm.pharm.maayanlab.Harmonizome.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.UserSearchDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.ResourceStatsSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(urlPatterns = {
	"/" + Constant.API_URL + "/words",
	"/" + Constant.API_URL + "/words" + "/"
})
public class WordsAPI extends HttpServlet {

	private static final long serialVersionUID = -2370972153895413361L;

	private static final Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Resource.class, new ResourceStatsSerializer());
		gson = gsonBuilder.create();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateUtil.beginTransaction();
			PrintWriter out = response.getWriter();
            List<String> searchedWords = UserSearchDao.getAll();
            String json = gson.toJson(searchedWords);
			out.write(json);
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