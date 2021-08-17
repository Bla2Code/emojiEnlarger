package com.bla2code.emojiservice.service.impl;

import com.bla2code.emojiservice.service.CommandService;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PongServiceImpl implements CommandService {

    @Override
    public Mono<Message> processCommand(Message eventMessage) {

        System.out.println(eventMessage.getContent());

        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!pong"))
                .flatMap(Message::getChannel)
                .flatMap(channel ->
                        channel.createMessage("Ping!"))
                ;

    }

}