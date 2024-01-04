package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;

import org.json.JSONObject;
import org.json.JSONArray;

import edu.mssm.pharm.maayanlab.Harmonizome.net.OpenAiApiController;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;


@WebServlet(urlPatterns = { "/chat" })
public class ChatPage extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("history", OpenAiApiController.createHistory());
        request.getRequestDispatcher(Constant.TEMPLATE_DIR + "chat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray history = new JSONArray(request.getParameter("history"));
        history = OpenAiApiController.chatGPT(history, true);
        response.getWriter().write(history.toString());
    }
}
