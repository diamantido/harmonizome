package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { "/feedback" })
public class FeedbackPage extends HttpServlet {

	private static final long serialVersionUID = 4844105686029294800L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "feedback.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topic = request.getParameter("topic");
		String email = request.getParameter("email");
		String text = request.getParameter("text");
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "feedback-thanks.jsp").forward(request, response);
	}
}