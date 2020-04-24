package pl.coderslab.workshop3.controller;

import pl.coderslab.workshop3.dao.SolutionDao;
import pl.coderslab.workshop3.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "home", urlPatterns = "/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numberSolutions = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        SolutionDao solutionDao = new SolutionDao();
        List<Solution> recentSolutions = solutionDao.findRecent(numberSolutions);
        request.setAttribute("recentSolutions", recentSolutions);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
