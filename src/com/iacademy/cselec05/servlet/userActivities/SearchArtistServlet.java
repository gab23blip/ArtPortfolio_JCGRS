package com.iacademy.cselec05.servlet.userActivities;

import com.iacademy.cselec05.factory.ObjectFactory;
import com.iacademy.cselec05.repo.ArtDatabaseRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *              This is the SearchArtistServlet
 *              Main purpose is to list searched artist
 *
 */

public class SearchArtistServlet extends HttpServlet {

    // as usual we will have the object factory and the database repo
    public static final ObjectFactory objectFactory = new ObjectFactory();
    public static final ArtDatabaseRepo databaseRepo = objectFactory.getArtRepistory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/searchArtist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: search logic here -- DONE

        // We are going to make it simple
        String artistName = req.getParameter("artistName");
        String artist = databaseRepo.getArtist(artistName);

        req.getSession().setAttribute("searchedArtist", artist);
        resp.sendRedirect(req.getContextPath() + "/profile/user-activity/result-artist");
    }
}
