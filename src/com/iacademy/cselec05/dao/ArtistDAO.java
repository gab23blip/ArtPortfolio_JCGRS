package com.iacademy.cselec05.dao;

import com.iacademy.cselec05.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

/**
 *          What this class does is it returns artists instead of posts
 *          It will have the functions to return the artists and specific artist
 */

public class ArtistDAO {

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

    public String getSpecificArtist(String artistName) {
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
