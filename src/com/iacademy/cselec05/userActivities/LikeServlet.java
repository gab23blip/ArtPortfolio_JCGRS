package com.iacademy.cselec05.userActivities;

/*
*       This is the like servlet that will serve as the
*       main controller for the servlet
*
* */


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

public class LikeServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int artistId = Integer.parseInt(request.getParameter("artistId"));

        // HttpSession session = request.getSession();
        // int userId = (Integer) session.getAttribute("user_id");

        User user = SessionUtil.getUser(request);

        if (user == null) {
            response.sendRedirect("/home");
            return;
        }

        int userId = user.getUserId();

        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement insertStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        try {

            conn = DBConnection.getConnection();

            // Check if already liked
            String checkSql = "SELECT * FROM liker WHERE liker_id=? AND artist_id=?";
            checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, artistId);

            rs = checkStmt.executeQuery();

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
