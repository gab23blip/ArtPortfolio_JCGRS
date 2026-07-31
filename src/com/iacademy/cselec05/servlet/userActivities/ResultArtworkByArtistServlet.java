package com.iacademy.cselec05.servlet.userActivities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Servlet that serves the search artworks by artist here
// We only need the doGet here
// And this is much better than having the InsertServlet or MyServlet doing everything at once
// Which is a big W fam -- I am just saying
public class ResultArtworkByArtistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/resultArtworkByArtist.jsp").forward(req, resp);
    }
}
