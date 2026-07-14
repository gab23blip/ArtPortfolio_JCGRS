package com.iacademy.cselec05.servlet.profile;

import com.iacademy.cselec05.factory.databaseFactory;
import com.iacademy.cselec05.model.artDomain;
import com.iacademy.cselec05.util.artDatabaseRepo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        artDatabaseRepo dataRepo = databaseFactory.newOne();
        ArrayList<artDomain> feedPosts = dataRepo.retrieveallstuff();

        for (artDomain pictureForShowing : feedPosts) {
            byte[] toConvert = pictureForShowing.getArtPhoto();
            if (toConvert != null) {
                pictureForShowing.setConvertedPicture(Base64.getEncoder().encodeToString(toConvert));
            }
        }

        request.setAttribute("feedPosts", feedPosts);
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }
}
