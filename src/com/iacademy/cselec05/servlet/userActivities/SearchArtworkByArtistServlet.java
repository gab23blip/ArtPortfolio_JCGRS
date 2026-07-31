package com.iacademy.cselec05.servlet.userActivities;

import com.iacademy.cselec05.factory.ObjectFactory;
import com.iacademy.cselec05.model.ArtDomain;
import com.iacademy.cselec05.repo.ArtDatabaseRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/*
*           This is the SearchArtworkByArtistServlet - Juan Amado Cleto
*           We are going to cheat a bit -- and by cheat I mean have a different servlet getting the results
*           That way it will be easier to trace.
*
*           What this servlet does is simply getting the parameters from request and then forwarding it to another jsp
*           file -- basically like the success.jsp from past activities
*
* */

public class SearchArtworkByArtistServlet extends HttpServlet {

    // as usual we will have the object factory and the database repo
    public static final ObjectFactory objectFactory = new ObjectFactory();
    public static final ArtDatabaseRepo databaseRepo = objectFactory.getArtRepistory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/searchArtworkByArtist.jsp").forward(request,response);
    }

    // get the request parameters here
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: search logic here -- DONE

        String artistName = req.getParameter("artistName");
        List<ArtDomain> artDomainList = databaseRepo.retrieveDataFromArtist(artistName);

        // Converts the image
        for(ArtDomain pictureForShowing: artDomainList)
        {
            byte [] toConvert = pictureForShowing.getArtPhoto();
            pictureForShowing.setConvertedPicture(Base64.getEncoder().encodeToString(toConvert));
        }

        req.getSession().setAttribute("artworkByArtistList", artDomainList);
        resp.sendRedirect(req.getContextPath() + "/profile/user-activity/result-artwork-by-artist");
    }
}
