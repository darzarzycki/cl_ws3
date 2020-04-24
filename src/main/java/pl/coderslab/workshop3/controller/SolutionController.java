package pl.coderslab.workshop3.controller;

import pl.coderslab.workshop3.dao.SolutionDao;
import pl.coderslab.workshop3.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "solution", urlPatterns = "/solution")
public class SolutionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParameter = request.getParameter("id");
        int id = Integer.parseInt(idParameter);
        SolutionDao solutionDao = new SolutionDao();
        Solution solution = solutionDao.readSolution(id);
        request.setAttribute("solution", solution);
        request.getRequestDispatcher("/solution.jsp")
                .forward(request, response);
    }
}
