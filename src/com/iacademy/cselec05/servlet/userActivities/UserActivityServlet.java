package com.iacademy.cselec05.servlet.userActivities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* Insert Servlet is now deprecated for several reasons - Juan Amado Cleto (I am addicted to grades like coke
* and Scarface)
* 1. It's doing everything at the same time -- again we don't want that
* 2. To solve it -- I am going to use page for this that will have the options (search, insert) and so on
* 3. As for the posts it will reflect in the homefeed instead of the InsertServlet
* 4. This is much easier to do than having the insert servlet doing everything at the same time.
* */

public class UserActivityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/userActivity.jsp").forward(req, resp);
    }
}
