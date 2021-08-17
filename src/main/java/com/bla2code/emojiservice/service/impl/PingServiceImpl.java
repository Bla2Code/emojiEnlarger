package com.bla2code.emojiservice.service.impl;

import com.bla2code.emojiservice.service.CommandService;
import com.bla2code.emojiservice.service.MessageService;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PingServiceImpl implements CommandService {

    @Override
    public Mono<Message> processCommand(Message eventMessage) {

        System.out.println(eventMessage.getContent());

        return eventMessage.getChannel()
                .flatMap(channel -> channel.createMessage("Pong!"));
    }

}