package com.yourname.ticketbooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDAO {
    public static void saveTicket(String name, String email, String event) {
        String dbUrl = "jdbc:mysql://localhost:3306/ticketdb";
        String dbUser = "root";
        String dbPassword = "yourpassword";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO tickets (name, email, event) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, event);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
