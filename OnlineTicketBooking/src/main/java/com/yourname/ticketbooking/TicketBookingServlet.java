package com.yourname.ticketbooking;

import com.yourname.ticketbooking.dao.TicketDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/api/ticket/book")
public class TicketBookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String event = request.getParameter("event");

        try {
            // Save the ticket using TicketDAO
            TicketDAO.saveTicket(name, email, event);

            // Redirect to confirmation page
            response.sendRedirect("/confirmation?name=" + name + "&email=" + email + "&event=" + event);
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to book your ticket. Please try again.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }
}
