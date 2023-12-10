package com.example.SpringBootSpectra.Services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ACU {
    private final RabbitTemplate rabbitTemplate;

    public ACU(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }
    @RabbitListener(queues = "SpectraToACU")
    public void AnnounceTicket(@RequestBody String announcement)
    {
        System.out.println(announcement + " please proceed to the counter");
    }
}
