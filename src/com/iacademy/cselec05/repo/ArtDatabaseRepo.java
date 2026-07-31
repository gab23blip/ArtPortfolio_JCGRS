package com.iacademy.cselec05.repo;

import com.iacademy.cselec05.dao.ArtistDAO;
import com.iacademy.cselec05.dao.ImageDAO;
import com.iacademy.cselec05.model.*;

import java.util.List;
import java.util.Set;

/*
* Few changes here -- Juan Amado Cleto
* 1. Centralized the whole thing to one database connection
* 2. Using Tayao's code from the DB connection -- I just simply call getConnection()
* 3. Created an sql dump to create artist with tables art_photo artist_name art_name with auto incremented id
* 4. Comments
* 5. Always have the class name as pascal case -- it goes against convention to have class name into camel case. Hence
* I renamed it to ArtDatabaseRepo -- always remember a class name should always be in pascal case -- not camel case.
* 6. I placed it inside package because it should not be inside util package
* 7. This is fundamentally better -- also for more improvement have a try catch for each query. This will help if any error
* is encountered will have the code exit gracefully eventhough I think that's cheesy not gonna lie
* */
public class ArtDatabaseRepo
{
    // TODO: will change this to a local database -- DONE
    /*
    private String url = "jdbc:mysql://172.104.165.179:3306/cselec05";
    private String user = "student";
    private String password = "iacademy";
    */

    private static final ImageDAO imageDAO = new ImageDAO();
    private static final ArtistDAO artistDao = new ArtistDAO();


    // What this function does is insert Art Piece using a parameter insertArtPiece of class ArtDomain
    // changed to uploadArt because this is what we are essentially doing 'uploading' and it is more meaningful
    public void uploadArt(ArtDomain insertArtPiece)
    {
        imageDAO.upload(insertArtPiece);
    }

    // This one should be renamed to retrieve data -- but for a project I will let this slide
    // Anyways, this function retrieves the data from the database into an arraylist named posts
    // and returns it
    // Changed retrievestuff to its proper name
    // I know its long but the intent is clear because we are receiving data from a specific artist
    // Cleared up a warning because this should be List<ArtDomain>
    public List<ArtDomain> retrieveDataFromArtist(String artistName)
    {
        return imageDAO.getSpecificDataFromArtist(artistName);

    }

    // Okay this is the same with retrieveStuff but this one lists all the posts from the arraylist
    // change the name to show all art data
    // Okay named it to retrieveAllData because it sounds more fitting
    // So what this function is that retrieves all data from the table artist -- okay sounds good
    public List<ArtDomain> getPosts()
    {
        return imageDAO.getPosts();
    }

    // The reason why it is a set class is because I do not want any wasted memory
    // This will be called in ListArtistsServlet
    public Set<String> getArtists() {

        return artistDao.getArtists();
    }

    // And besides we are only aiming to get the String here to list the artists
    // We are going to make it simple -- we are not going to use a set but rather we are going to use
    // a string -- this function returns a string named artistName.
    // This is for searching by artist name
    public String getArtist(String artistName) {
        return artistDao.getSpecificArtist(artistName);
    }
}
