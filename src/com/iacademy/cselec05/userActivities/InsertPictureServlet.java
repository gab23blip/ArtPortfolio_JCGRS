package com.iacademy.cselec05.userActivities;

import com.iacademy.cselec05.factory.ObjectFactory;
import com.iacademy.cselec05.model.ArtDomain;
import com.iacademy.cselec05.repo.ArtDatabaseRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
        New Insert Picture Servlet -- Juan Amado Cleto
        Unlike the deprecated InsertServlet formally known as MyServlet

        This only does one thing -- insert pictures -- ah I can hear mad artists -- those anti AI people but anyways
        this is part of my refractoring part holy crap (and I am sorry for the developer who did the insert logic) but
        oof -- this could have been better. But enough of me complaining.

        To be fair for the developer, the logic is already there -- I am going to reuse his logic particularly the inserting
        one and let's see what can be done.
 */

@MultipartConfig
public class InsertPictureServlet extends HttpServlet {


    // like in the delivery activity from sir's class we need this object factory to produce well objects
    // this will have a part in doPost
    public static ObjectFactory objectFactory = new ObjectFactory();

    // get the 'predestined elements and render it through doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/insertPicture.jsp").forward(request,response);
    }

    // Copied the logic from doPost from InsertServlet
    // Few reminders to me and the other developers
    // proper naming conventions ---- we cannot use 'unusualAction' as a variable name. LMAO
    // Because this tells me or other programmers nothing.
    // So we need to have proper naming conventions for development
    // because somebody will maintain our code, or do keep in mind of other developers
    // not going insane. Proper naming conventions = what this thing (variable or function or class) does.
    // I will change the unusualAction into something more meaningful
    // TODO: Investigate what unusualAction does -- REMOVED because we don't need it anymore
    // TODO: remove the blob for it will bloat the table or the database LMAO
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String artistName = request.getParameter("artist"); // The artist name
        String artPieceName = request.getParameter("artName"); // The art piece name
        ArtDomain domain = new ArtDomain();
        Part Picture = request.getPart("Picture"); // Gets the inputed picture in its raw version
        byte[] finalPhoto = getBytes(Picture);
        domain.setArtPhoto(finalPhoto);
        domain.setArtName(artPieceName);
        domain.setArtistName(artistName);

        ArtDatabaseRepo dataRepo = objectFactory.getArtRepistory();
        dataRepo.uploadArt(domain);
        // This will be in the homefeed
        //request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/home");

    }

    // Added this because it is giving me a warning -- I dont like warnings
    // It is simply a helper function to get the bytes
    // And because of this -- good news we dont need 'VAGUE' statements like file uploads -- where is the uploads so yeah
    // Anyways this simplifies things because we only need the database and that's it.
    // And luckily, SQL can stores thousands or even millions from my experience
    private static byte[] getBytes(Part Picture) throws IOException {
        InputStream processedPicture = Picture.getInputStream(); // Gets the byte version of the picture

        ByteArrayOutputStream getThePicture = new ByteArrayOutputStream();
        byte [] chosenPicture = new byte[9000];
        int readByte = 0;
        while((readByte = processedPicture.read(chosenPicture,0,chosenPicture.length)) !=-1)
        {
            getThePicture.write(chosenPicture,0,readByte);
        }// This entire sequence from ByteArrayOutputStream to the while loop is convreting said picture bytes into bytes that can fit in a array

        byte [] finalPhoto = getThePicture.toByteArray(); // Puts picture bytes into an array
        return finalPhoto;
    }
}
