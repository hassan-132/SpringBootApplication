package com.example.SpringBootSpectra.Controller;

import com.example.SpringBootSpectra.Model.Ticket;
import com.example.SpringBootSpectra.Repo.TicketRepo;
import com.example.SpringBootSpectra.Services.Kiosk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
public class SpectraController {

    @Autowired
    private TicketRepo ticketRepo;

    private static final Logger logger = LoggerFactory.getLogger(SpectraController.class);
    LinkedBlockingQueue<String > Callqueue = new LinkedBlockingQueue<String>();
    Iterator <String> nextCall = Callqueue.iterator();
    private final RabbitTemplate rabbitTemplate;
    public SpectraController(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Autowired
    Kiosk kiosk;
    @PostMapping("/generateTicket")
    public void generateTicket(@RequestBody String req)
    {
        logger.info("API generateTicket has been hit");
        String reqType = req;
        kiosk.GenerateTicket(reqType);
    }
    @RabbitListener(queues = "KioskToSpectra")
    public void kioskListener(@RequestBody String message)
    {
        System.out.println("Ticket " + message + " has been generated");
        rabbitTemplate.convertAndSend("SpectraToWebTSU",message);
    }
    @RabbitListener(queues = "WebTSUToSpectra")
    public void recCall(@RequestBody String message)
    {
        Callqueue.add(message);
    }

    @GetMapping("/currentTickets")
    public void currTickets()
    {
        logger.info("API currentTicket has been hit");
        System.out.println("Tickets currently in Waiting \n" + Callqueue);
    }
    @GetMapping("/callTicket")
    public void callTicket()
    {
        logger.info("API callTicket has been hit");
        String call = Callqueue.poll();
        if (call != null) {
            System.out.println(call);
            String temp [];
            temp = call.split("\\s+"); // code gets stuck here
            String ann = temp[2];
            rabbitTemplate.convertAndSend("SpectraToACU",ann);
        }
        else{
            System.out.println("No call Scheduled, Queue is empty");
        }
    }
}
