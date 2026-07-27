package com.iacademy.cselec05.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.iacademy.cselec05.model.User;
import com.iacademy.cselec05.util.DBConnection;
import com.iacademy.cselec05.util.PasswordUtil;

// Again, commenting is always a good practice soooo what I am going to do is comment this stuff and trace it --- like
// a good developer. But anyways I am adding comments to trace the logic -- Juan Amado Cleto

// User Dao class is for registering, authenticating, updating users, and delete users
public class UserDAO {
    // This registers user
    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (username, email, password_hash) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, PasswordUtil.hashPassword(user.getPasswordHash()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return false; // Moved it here because we are telling the code that any other usecase aside from the usecase
                        // that is assigned to it will return false
                        // And we must ensure that the return is reached.
    }

    // authenticate user
    public User authenticateUser(String usernameOrEmail, String password) {
        String sql = "SELECT * FROM user WHERE username = ? OR email = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usernameOrEmail);
            ps.setString(2, usernameOrEmail);
            rs = ps.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");
                if (PasswordUtil.verifyPassword(password, hashedPassword)) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPasswordHash(hashedPassword);
                    user.setBio(rs.getString("bio"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return null; // Return null
    }

    // Updates user
    public boolean updateUserSettings(int userId, String username, String email, String password) {
        boolean updatePassword = (password != null && !password.trim().isEmpty());
        String sql = updatePassword
                ? "UPDATE user SET username = ?, email = ?, password_hash = ? WHERE user_id = ?"
                : "UPDATE user SET username = ?, email = ? WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            if (updatePassword) {
                ps.setString(3, PasswordUtil.hashPassword(password));
                ps.setInt(4, userId);
            } else {
                ps.setInt(3, userId);
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return false;
    }

    // deletes user
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return false;
    }

    // ==============================================================
    // Helper Functions
    // ==============================================================
    // Helper function section

    // Okay we have to change doesUsernameExist -- while it is useful to have "is" + Something, we have to be aware that
    // we can use does, has and so on.
    // This function is a helper function
    public boolean isUsernameExists(String username) {
        String sql = "SELECT 1 FROM user WHERE username = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Okay from
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next(); // this returns true
        } catch (SQLException e) {
            e.printStackTrace();
            //return false;
        } finally {
            // Good, we have finally to close the close the connection, the result set and the prepared statement
            // five stars for that
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return false; // I put it here because we are assuming we only want one usecase -- the rest, ignored or false.
    }

    // again this could changed to doesEmailExist because it is more correct
    // this function is a helper function
    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM user WHERE email = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return false; // I moved it here
    }

    // This will be used to retreive ID from the database
    public int getUserIdFromDatabase(String email) {
        int userId = -1;

        String sql = "SELECT user_id FROM users WHERE email = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("user_id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
}
