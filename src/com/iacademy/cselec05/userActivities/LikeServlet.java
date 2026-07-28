package com.iacademy.cselec05.userActivities;

import com.iacademy.cselec05.model.User;
import com.iacademy.cselec05.util.DBConnection;
import com.iacademy.cselec05.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
            This servlet's purpose is a controller for the like feature
            It has a do post that will listen for the artist id

            1. One thing to note make sure the artist table has a primary key not null and is auto incremented
               this is because it is going to throw an SQL exception as it is trying to find the id of both liker
               table and artist table

            -- Juan Amado Cleto
 */

public class LikeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // I changed the sql dump from id to artist id
        int artistId = Integer.parseInt(request.getParameter("artistId"));

        // HttpSession session = request.getSession();
        // int userId = (Integer) session.getAttribute("user_id");

        // Grab the user from the session because we are going to need its id
        User user = SessionUtil.getUser(request);

        // Some control flow in order to ensure that it is null
        if (user == null) {
            response.sendRedirect("/home");
            return;
        }

        // grab the user id
        int userId = user.getUserId();

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

                response.getWriter().println("Liked!");

            } else {

                response.getWriter().println("Already liked.");

            }

        } catch (SQLException sex) {

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

            try { if(rs != null) rs.close(); } catch(Exception e){}
            try { if(checkStmt != null) checkStmt.close(); } catch(Exception e){}
            try { if(insertStmt != null) insertStmt.close(); } catch(Exception e){}
            try { if(updateStmt != null) updateStmt.close(); } catch(Exception e){}
            try { if(conn != null) conn.close(); } catch(Exception e){}

        }

    }
}
