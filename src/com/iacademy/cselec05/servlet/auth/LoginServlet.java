package com.iacademy.cselec05.servlet.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.iacademy.cselec05.dao.UserDAO;
import com.iacademy.cselec05.model.User;
import com.iacademy.cselec05.util.SessionUtil;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String usernameOrEmail = request.getParameter("usernameOrEmail");
        String password = request.getParameter("password");

        User user = userDAO.authenticateUser(usernameOrEmail, password);
        if (user != null) {
            SessionUtil.setSessionAttribute(request, "user", user);
            response.sendRedirect(request.getContextPath() + "/home");
        }
        else {
            request.setAttribute("error", "Invalid username/email or password.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}
