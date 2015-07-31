package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.json.BaseSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;


@WebServlet(urlPatterns = { "/" + Constant.API_URL, "/" + Constant.API_URL + "/*" })
public class API extends HttpServlet {

	private static final long serialVersionUID = -6205859300423151010L;

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseSchema baseJson = new BaseSchema();
		String json = gson.toJson(baseJson);
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
	}
}