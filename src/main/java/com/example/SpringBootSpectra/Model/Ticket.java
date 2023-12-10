package com.example.SpringBootSpectra.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int TicketID;
    @Column(name = "ticket_no")
    String TicketNo;
    @Column(name = "ticket_type")
    char TicketType;
    public Ticket()
    {

    }
    public Ticket(String TicketNo,char TicketType)
    {
        this.TicketNo = TicketNo;
        this.TicketType = TicketType;
    }
    public String getTicketNo() {
        return TicketNo;
    }

    public void setTicketNo(String ticketNo) {
        TicketNo = ticketNo;
    }

    public char getTicketType() {
        return TicketType;
    }

    public void setTicketType(char ticketType) {
        TicketType = ticketType;
    }
    public String makeTicket()
    {
        String ticket = TicketType + TicketNo;
        return ticket;
    }
}
