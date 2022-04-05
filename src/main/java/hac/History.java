package hac;

import com.google.gson.Gson;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "History", urlPatterns = {"/history"})
public class History extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // store JsonData in servlet context
        HistoryData jsonData = new HistoryData();
        getServletContext().setAttribute("jsonData", jsonData);
        System.out.println("History servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // get jsondata from servlet context
            HistoryData jsonData = (HistoryData) getServletContext().getAttribute("jsonData");

            String op = request.getParameter("left") + "+" + request.getParameter("right") + "=" + request.getAttribute("result");
            jsonData.addHistory(op);
            // this won't really work for multiple users - what is wrong with this solution?
            jsonData.setLastResult((Integer) request.getAttribute("result"));

            // return gson object
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(jsonData));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
