package com.iacademy.cselec05.servlet.userActivities;

import com.iacademy.cselec05.dao.ImageDAO;
import com.iacademy.cselec05.model.User;
import com.iacademy.cselec05.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        ImageDAO imageDAO = new ImageDAO();
        // I changed the sql dump from id to artist id
        int artistId = Integer.parseInt(request.getParameter("artistId"));

        // Grab the user from the session because we are going to need its id
        User user = SessionUtil.getUser(request);

        // Some control flow in order to ensure that it is null
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // grab the user id
        int userId = user.getUserId();

        try {
            imageDAO.like(response, request, userId, artistId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
