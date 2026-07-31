package com.iacademy.cselec05.servlet.userActivities;

import com.iacademy.cselec05.factory.ObjectFactory;
import com.iacademy.cselec05.repo.ArtDatabaseRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/*
*           What this class does is its a servlet
*           That renders the page listArtists.jsp
*
* */


public class ListArtistsServlet extends HttpServlet {

    // as usual we will have the object factory and the database repo
    public static final ObjectFactory objectFactory = new ObjectFactory();
    public static final ArtDatabaseRepo databaseRepo = objectFactory.getArtRepistory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Set<String> artists;
        artists = databaseRepo.getArtists();

        req.getSession().setAttribute("artists", artists);

        req.getRequestDispatcher("/WEB-INF/pages/listArtists.jsp").forward(req, resp);
    }
}
