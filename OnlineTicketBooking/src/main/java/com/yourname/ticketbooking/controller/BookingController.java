package com.yourname.ticketbooking.controller;

import com.yourname.ticketbooking.model.Ticket;
import com.yourname.ticketbooking.service.EmailService;
import com.yourname.ticketbooking.util.QRCodeGenerator;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/api/ticket")
public class BookingController {

    @PostMapping("/book")
    public String bookTicket(@RequestBody Ticket ticket) {
        String qrCodePath = "tickets/" + ticket.getTicketId() + ".png";

        try {
            QRCodeGenerator.generateQRCode(ticket.getTicketId(), qrCodePath);
            String emailBody = "Thank you for booking your ticket. Your ticket ID is " + ticket.getTicketId();
            EmailService.sendEmail(ticket.getEmail(), "Your Ticket Confirmation", emailBody, qrCodePath);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
            return "Failed to book ticket";
        }

        return "Ticket booked successfully! Check your email for the ticket.";
    }
}
