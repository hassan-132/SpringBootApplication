package com.example.SpringBootSpectra.Services;

import com.example.SpringBootSpectra.Model.Ticket;
import com.example.SpringBootSpectra.Repo.TicketRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Kiosk {

    @Autowired
    private TicketRepo ticketRepo;
    static int count1 = 0,count2 = 0;
    private static RabbitTemplate rabbitTemplate;
    public Kiosk(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void GenerateTicket(String reqType)
    {
        Ticket ticket = new Ticket();
        switch (reqType)
        {
            case "CashDebit" :
                count1++;
                ticket.setTicketNo(Integer.toString(count1));
                ticket.setTicketType('A');
                ticketRepo.save(ticket);
                break;
            case "CashCredit" :
                count2++;
                ticket.setTicketNo(Integer.toString(count2));
                ticket.setTicketType('B');
                ticketRepo.save(ticket);
                break;
        }
        String generatedTicket = ticket.makeTicket();
        rabbitTemplate.convertAndSend("KioskToSpectra",generatedTicket);
    }

//    private static void SaveTicket(Ticket obj)
//    {
//        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
//             Session session = factory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(obj);
//            transaction.commit();
//        }
//    }

}
