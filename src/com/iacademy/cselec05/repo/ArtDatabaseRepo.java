package com.iacademy.cselec05.repo;

import com.iacademy.cselec05.model.*;
import com.iacademy.cselec05.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
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


    // What this function does is insert Art Piece using a parameter insertArtPiece of class ArtDomain
    // changed to uploadArt because this is what we are essentially doing 'uploading' and it is more meaningful
    public void uploadArt(ArtDomain insertArtPiece)
    {
        // Set to null because we are going to utilize a close function from DBConnect
        Connection connect = null;
        PreparedStatement prepare = null;
        // good we have this statement
        String insertQuery = "INSERT INTO artist (art_photo, artist_name, art_name) VALUES(?,?,?)";

        // will keep try catch -- it is a good habit to have  -- good job!
        try
        {
            // removed class for name because it is already declared inside the DBConnection
            connect = DBConnection.getConnection(); // Okay this is good that we have a static connection
            prepare = connect.prepareStatement(insertQuery); // preparedStatement here
            prepare.setBytes(1,insertArtPiece.getArtPhoto()); // we need table column bytes for our local database
            prepare.setString(2,insertArtPiece.getArtistName());
            prepare.setString(3,insertArtPiece.getArtName());
            // prepare.setInt(4, 0);
            prepare.executeUpdate();


        }
        catch (SQLException e) // thinking of having the SQLException for catch but -- eeeeeh too lazy
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        finally {
            DBConnection.close(connect);
            DBConnection.close(prepare);
        }
    }

    // This one should be renamed to retrieve data -- but for a project I will let this slide
    // Anyways, this function retrieves the data from the database into an arraylist named posts
    // and returns it
    // Changed retrievestuff to its proper name
    // I know its long but the intent is clear because we are receiving data from a specific artist
    // Cleared up a warning because this should be List<ArtDomain>
    public List<ArtDomain> retrieveDataFromArtist(String artistName)
    {
        // Alright for this one it we should have the List post here
        // This is because if we were to use post -- the list -- it is redundant
        // no --- it should not be there. Instead we should opt for volatile memory purely because
        // if we think about it we are only listing what the art work is and it should not be remembered by the machine.
        // Therefore the solution for this is to have a volatile List inside this function that is only there to retrieve
        // Once the server refreshes then it can shove off. However there is a danger here if it is volatile then there is
        // this danger of memory leakage -- we don't want but for this project sure
        List<ArtDomain> tempPost = new ArrayList<>();

        String retrieveQuery = "SELECT * FROM artist WHERE artist_name = ?";

        Connection connect = null;
        PreparedStatement retrieveDataQuery = null;
        ResultSet result = null;

        try
        {
            connect = DBConnection.getConnection();
            retrieveDataQuery = connect.prepareStatement(retrieveQuery);
            retrieveDataQuery.setString(1,artistName);

            result = retrieveDataQuery.executeQuery();

            while (result.next())
            {
                ArtDomain retrievePosts = new ArtDomain();
                retrievePosts.setArtName(result.getString("art_name"));
                retrievePosts.setArtistName(result.getString("artist_name"));
                retrievePosts.setArtPhoto(result.getBytes("art_photo"));
                retrievePosts.setLikeCount(result.getInt("like_count"));
                tempPost.add(retrievePosts);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            DBConnection.close(connect);
            DBConnection.close(retrieveDataQuery);
            DBConnection.close(result);
        }

        // We are returning tempPost -- if the user were to search a post from an artist have another List inside the servlet
        // to serve the posts -- ah yes I love Postal hahaha
        return tempPost;

    }

    // Okay this is the same with retrieveStuff but this one lists all the posts from the arraylist
    // change the name to show all art data
    // Okay named it to retrieveAllData because it sounds more fitting
    // So what this function is that retrieves all data from the table artist -- okay sounds good
    public List<ArtDomain> getPosts()
    {
        String retrieveQuery = "SELECT * FROM artist";
        Connection connect = null;
        PreparedStatement getPostsQuery = null;
        ResultSet result = null;


        // I put it here because it is going to duplicate things if user refreshes page
        List<ArtDomain> posts = new ArrayList<>();

        try
        {
            connect = DBConnection.getConnection();
            getPostsQuery = connect.prepareStatement(retrieveQuery);
            result = getPostsQuery.executeQuery();

            while (result.next())
            {
                ArtDomain retrievePosts = new ArtDomain();
                retrievePosts.setArtistId(result.getInt("artist_id"));
                retrievePosts.setArtName(result.getString("art_name"));
                retrievePosts.setArtistName(result.getString("artist_name"));
                retrievePosts.setArtPhoto(result.getBytes("art_photo")); // ANOTHER PROBLEM SOLVED
                                                                                    // Replace this with a string for file path
                retrievePosts.setLikeCount(result.getInt("like_count"));
                posts.add(retrievePosts);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            DBConnection.close(connect);
            DBConnection.close(getPostsQuery);
            DBConnection.close(result);
        }
        return posts;
    }

    // The reason why it is a set class is because I do not want any wasted memory
    // This will be called in ListArtistsServlet
    public Set<String> getArtists() {

        Connection connect = null;
        PreparedStatement getArtistQuery = null;
        ResultSet resultSet = null;

        // Selects only the artist name column from every row in the table
        String retrieveArtistQuery = "SELECT artist_name FROM artist";
        Set<String> artists = new HashSet<>();

        try {

            connect = DBConnection.getConnection();
            getArtistQuery = connect.prepareStatement(retrieveArtistQuery);
            resultSet = getArtistQuery.executeQuery();
            // Loop through every single artist row in the database
            while (resultSet.next()) {
                artists.add(resultSet.getString("artist_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.close(connect);
            DBConnection.close(getArtistQuery);
            DBConnection.close(resultSet);
        }

        return artists;
    }

    // And besides we are only aiming to get the String here to list the artists
    // We are going to make it simple -- we are not going to use a set but rather we are going to use
    // a string -- this function returns a string named artistName.
    // This is for searching by artist name
    public String getArtist(String artistName) {

        Connection connect = null;
        PreparedStatement getSpecificArtistQuery = null;
        ResultSet resultSet = null;

        // retrieve artist query limit 1 gives us only -- its there in the sql string
        String retrieveArtistQuery = "SELECT artist_name FROM artist WHERE artist_name = ? LIMIT 1";

        try {
            connect = DBConnection.getConnection();
            getSpecificArtistQuery = connect.prepareStatement(retrieveArtistQuery);

            getSpecificArtistQuery.setString(1, artistName);
            resultSet = getSpecificArtistQuery.executeQuery();

            // if found return
            if (resultSet.next()) {
                return resultSet.getString("artist_name"); // Returns the found name
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.close(connect);
            DBConnection.close(getSpecificArtistQuery);
            DBConnection.close(resultSet);
        }

        return null; // Returns null if the artist does not exist
    }

}
