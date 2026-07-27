package com.iacademy.cselec05.userActivities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*           This is the result artist servlet
*           it is responsible for search result if artist is found
*
* */
public class ResultArtistServlet extends HttpServlet {

    // we have do get to render resultArtist page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/resultArtist.jsp").forward(req, resp);
    }
}
