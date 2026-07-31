// COMMENTED THIS OUT BECAUSE WE ARE GOING TO SEPERATE THIS INTO DIFFERENT SERVLET
// DO NOT --- I REPREAT -- DO NOT UNCOMMENT BECAUSE IT IS GOING TO CRASH THE WHOLE PROGRAM


/*package com.iacademy.cselec05.userActivities;


import com.iacademy.cselec05.util.*;
import com.iacademy.cselec05.model.*;
import com.iacademy.cselec05.factory.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;

// OKAY, this is not clean -- Juan Amado Cleto
// why? Okay I appreciate the effort but this servlet is doing everything all at once
// We don't want that.
// To solve this -- we are going to rename InsertServlet into user activity servlet
// And have all the options there.
// So for the developer or developers -- do not be afraid to have multiple servlets
// Because 1. Java EE is obsolete -- I mean I prefer express js because there is no like
// ritual war file and XAMPP -- and that's an objective statement.
// This is a big no no -- because the servlet is doing everything everywhere at the same time.
// thus when running on XAMPP -- the pages will not render.
// What I am going to do is recycle this with only one purpose and that is insert only, no bull

public class InsertServlet extends HttpServlet
{
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        artDatabaseRepo dataRepo = databaseFactory.newOne();
        String jspPage = request.getParameter("page");


        if("insert".equals(jspPage))
        {
            request.setAttribute("inset",dataRepo);
            request.getRequestDispatcher("/WEB-INF/pages/insertPicture.jsp").forward(request,response);
        }
        else if ("retrieve".equals(jspPage))
        {
            String artist = request.getParameter("Artistname");
            ArrayList<artDomain> postList = dataRepo.retrievestuff(artist);

            for(artDomain pictureForShowing: postList)
            {
                byte [] toConvert=pictureForShowing.getArtPhoto();
                pictureForShowing.setConvertedPicture(Base64.getEncoder().encodeToString(toConvert));
            }

            request.setAttribute("postAspects",postList);
            request.setAttribute("inset",dataRepo);
            request.setAttribute("artist",artist);
            request.getRequestDispatcher("/WEB-INF/pages/resultArtworkByArtist.jsp").forward(request,response);
        }
        else if("search".equals(jspPage))
        {
            request.setAttribute("inset",dataRepo);
            request.getRequestDispatcher("/WEB-INF/pages/searchArtworkByArtist.jsp").forward(request,response);
        }
        else if("homefeed".equals(jspPage))
        {
            ArrayList<artDomain> forHomeFeed = dataRepo.retrieveallstuff();

            for(artDomain pictureForShowing: forHomeFeed)
            {
                byte [] toConvert=pictureForShowing.getArtPhoto();
                pictureForShowing.setConvertedPicture(Base64.getEncoder().encodeToString(toConvert));
            }

            request.setAttribute("inset",forHomeFeed);
            request.getRequestDispatcher("/WEB-INF/pages/homeFeed.jsp").forward(request,response);
        }


    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String artistName = request.getParameter("artist"); // The artist name
        String artPieceName = request.getParameter("artName"); // The art piece name
        String picture = request.getParameter("unusualAction"); // The picture of the art piece

        if("picture".equals(picture)) // Determines which page is the request from,usually the name is unusualAction but the value is different depending on the page
        {
            artDomain Domain = new artDomain();
            Part Picture = request.getPart("Picture"); // Gets the inputed picture in its raw version
            InputStream processedPicture = Picture.getInputStream(); // Gets the byte version of the picture

            ByteArrayOutputStream getThePicture = new ByteArrayOutputStream();
            byte [] chosenPicture = new byte[9000];
            int readByte = 0;
            while((readByte = processedPicture.read(chosenPicture,0,chosenPicture.length)) !=-1)
            {
                getThePicture.write(chosenPicture,0,readByte);
            }// This entire sequence from ByteArrayOutputStream to the while loop is convreting said picture bytes into bytes that can fit in a array

            byte [] finalPhoto = getThePicture.toByteArray(); // Puts picture bytes into an array
            Domain.setArtPhoto(finalPhoto);
            Domain.setArtName(artPieceName);
            Domain.setArtistName(artistName);

            artDatabaseRepo dataRepo = databaseFactory.newOne();
            dataRepo.insertArt(Domain);
            request.setAttribute("insert",dataRepo);
            request.getRequestDispatcher("/WEB-INF/views/insertPicture.jsp").forward(request,response);

        }
        else if ("retrieve".equals(picture))
        {
            artDatabaseRepo dataRepo = databaseFactory.newOne();
            String artist = request.getParameter("Artistname");

            ArrayList<artDomain> postList = dataRepo.retrievestuff(artist);

            for(artDomain pictureForShowing: postList)
            {
                byte [] toConvert=pictureForShowing.getArtPhoto();
                pictureForShowing.setConvertedPicture(Base64.getEncoder().encodeToString(toConvert));
            }
            request.setAttribute("postAspects",postList);
            request.getRequestDispatcher("/WEB-INF/views/searchArtworkByArtist.jsp").forward(request,response);
        }
    }
}
*/