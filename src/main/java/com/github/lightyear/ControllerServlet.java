package com.github.lightyear;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerServlet extends HttpServlet{
    private Resume factory = new Resume();
    private ResumeDAO resumeDAO = null;
    Map dataMap = new HashMap();

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        resumeDAO = new ResumeDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        String name = request.getParameter("name");

        try {
            switch (action) {
                case "/getInfo":
                    dataMap = resumeDAO.getData(name);
                    Resume resume = factory.resumeFactory(dataMap);
                    showResume(resume, request, response);
                    break;
                default://срабатывает при входе
                    showCandidateList(request, response);
                    break;
            }
        } catch (Exception e) {
            processingException(e, response);
        }
    }

    private void showResume(Resume resume, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<String> candidateList = resumeDAO.getCandidateList();
        request.setAttribute("candidateList", candidateList);
        request.setAttribute("profession", resume.getProfession());
        request.setAttribute("phone", resume.getPhone());
        request.setAttribute("email", resume.getEmail());
        request.setAttribute("repository", resume.getRepository());
        request.setAttribute("region", resume.getRegion());
        request.setAttribute("region", resume.getRegion());
        request.setAttribute("universityTitle", resume.getUniversityTitle());
        request.setAttribute("faculty", resume.getFaculty());
        request.setAttribute("company", resume.getCompany());
        request.setAttribute("time", resume.getTime());
        request.setAttribute("skillsList", resume.getSkills());

        RequestDispatcher dispatcher = request.getRequestDispatcher("candidatesForm.jsp");
        dispatcher.forward(request, response);
    }


    private void showCandidateList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<String> candidateList = resumeDAO.getCandidateList();
        request.setAttribute("candidateList", candidateList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("candidatesForm.jsp");
        dispatcher.forward(request, response);
    }


    //creates 2 reports
    private void processingException(Exception e, HttpServletResponse response) {
        try {
            PrintWriter pw = response.getWriter();
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                pw.println("Error 001, please contact the support service.");
            }

            if (e instanceof IOException) {
                e.printStackTrace(System.err);
                System.err.println("Failed to redirect or forward");
                System.err.println("Message: " + e.getMessage());
                pw.println("Error 002, please contact the support service.");
            }

            if (e instanceof ServletException) {
                e.printStackTrace(System.err);
                System.err.println("Failed to forward");
                System.err.println("Message: " + e.getMessage());
                pw.println("Error 003, please contact the support service.");
            }

        } catch (IOException IOe) {
            IOe.printStackTrace(System.err);
        }
    }
}