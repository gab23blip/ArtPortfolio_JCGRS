package com.iacademy.cselec05.servlet.profile;

import com.iacademy.cselec05.factory.ObjectFactory;
import com.iacademy.cselec05.model.ArtDomain;
import com.iacademy.cselec05.repo.ArtDatabaseRepo;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
            Servlet that renders or seeeeerrrrves the Home servlet responsible for homefeed
 */

public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // No usage yet, dont know what you are aiming here

    // Get the object factory
    private static final ObjectFactory objectFactory = new ObjectFactory();
    private static final ArtDatabaseRepo dataRepo = objectFactory.getArtRepistory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get the posts for the feed
        List<ArtDomain> postList = dataRepo.getPosts();

        for (ArtDomain pictureForShowing : postList) {
            byte[] toConvert = pictureForShowing.getArtPhoto();
            if (toConvert != null) {
                pictureForShowing.setConvertedPicture(Base64.getEncoder().encodeToString(toConvert));
            }
        }

        request.setAttribute("feedPosts", postList);
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }
}
