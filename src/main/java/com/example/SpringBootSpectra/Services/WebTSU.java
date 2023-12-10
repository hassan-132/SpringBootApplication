package com.example.SpringBootSpectra.Services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class WebTSU {
    private final RabbitTemplate rabbitTemplate;
    public WebTSU(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }
    @RabbitListener(queues = "SpectraToWebTSU")
    public void CallTicket(@RequestBody String message)
    {
        String Call = "Call Ticket " + message;
        rabbitTemplate.convertAndSend("WebTSUToSpectra",Call);
    }
}
