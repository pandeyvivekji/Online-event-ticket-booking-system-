package com.yourname.ticketbooking.model;

public class Ticket {
    private String name;
    private String email;
    private String event;
    private String ticketId;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }
    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
}
