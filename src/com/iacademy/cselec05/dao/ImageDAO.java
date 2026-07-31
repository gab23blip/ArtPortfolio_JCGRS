package com.iacademy.cselec05.dao;

import com.iacademy.cselec05.model.ArtDomain;
import com.iacademy.cselec05.util.DBConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// No image DAO so we're going to work on images
// TODO: delete this if we are not going to use this -- Okay I put some thought to it
// And will use this class for the JDBC logic
public class ImageDAO {

    // What this function does is implement the like feature
    public void like(HttpServletResponse response, HttpServletRequest request, int userId, int artistId) throws SQLException, IOException{
        // prepare the variables to make the sql operation
        Connection conn = null;

        // check statement is basically -- we need to ensure the liker id or the user id -- is already there in the liker table
        PreparedStatement checkStmt = null;

        // this is the insert statement for the liker table
        PreparedStatement insertStmt = null;

        // and then update the artist table increment the values within the column like_count by 1 if not liked
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        // Right now they are null but -- we are going to fill them up in the try block

        try {

            // We will get the conn from DB connection
            conn = DBConnection.getConnection();

            // Check if already liked
            String checkSql = "SELECT * FROM liker WHERE liker_id=? AND artist_id=?";
            checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, artistId);

            rs = checkStmt.executeQuery(); // this is going to return a truthy.

            if (!rs.next()) {

                // Insert like
                String insertSql = "INSERT INTO liker (liker_id, artist_id) VALUES (?,?)";
                insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, artistId);
                insertStmt.executeUpdate();

                // Increment count
                String updateSql =
                        "UPDATE artist SET like_count = like_count + 1 WHERE artist_id=?";
                updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, artistId);
                updateStmt.executeUpdate();

                response.sendRedirect(request.getContextPath() + "/home");

            } else {

                response.getWriter().println("Already liked.");

            }

        }
        catch (SQLException sex) {

            sex.printStackTrace();
            response.getWriter().println("SQL Exception!");
            response.getWriter().println(userId);
            response.getWriter().println(artistId);
            response.getWriter().println(sex.getMessage());

        } catch (IOException ioe) {
            ioe.printStackTrace();
            response.getWriter().println("ioe Exception!");
        }

        finally {

            try { if(rs != null) rs.close(); } catch(Exception e){
                e.printStackTrace();
            }
            try { if(checkStmt != null) checkStmt.close(); } catch(Exception e){
                e.printStackTrace();
            }
            try { if(insertStmt != null) insertStmt.close(); } catch(Exception e){
                e.printStackTrace();
            }
            try { if(updateStmt != null) updateStmt.close(); } catch(Exception e){
                e.printStackTrace();
            }
            try { if(conn != null) conn.close(); } catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public List<ArtDomain> getPosts() {
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
}
