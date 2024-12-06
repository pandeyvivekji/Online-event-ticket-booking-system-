package com.yourname.ticketbooking.controller;

import com.yourname.ticketbooking.model.Ticket;
import com.yourname.ticketbooking.service.EmailService;
import com.yourname.ticketbooking.util.QRCodeGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
@RequestMapping("/ticket")
public class BookingController {

    
    @GetMapping("/book")
    public String showBookingForm() {
        return "index";
    }

    
    @PostMapping("/book")
    public String bookTicket(@ModelAttribute Ticket ticket, Model model) {
        String qrCodePath = "src/main/resources/static/tickets/" + ticket.getTicketId() + ".png";

        try {
            // Generate QR Code
            QRCodeGenerator.generateQRCode(ticket.getTicketId(), qrCodePath);

            // Prepare and send the email
            String emailBody = "Thank you for booking your ticket. Your ticket ID is " + ticket.getTicketId();
            EmailService.sendEmail(ticket.getEmail(), "Your Ticket Confirmation", emailBody, qrCodePath);

            // Add success message to the model
            model.addAttribute("message", "Ticket booked successfully! Check your email for the ticket.");
            model.addAttribute("ticket", ticket);

        } catch (IOException | MessagingException e) {
            e.printStackTrace();

            // Add failure message to the model
            model.addAttribute("message", "Failed to book ticket. Please try again.");
        }

        return "confirmation"; 
    }
}
