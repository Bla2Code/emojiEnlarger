package com.bla2code.emojiservice.service.impl;

import com.bla2code.emojiservice.service.MessageService;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public Mono<Void> processCommand(Message eventMessage) {

        System.out.println(eventMessage.getContent());

        Mono<Void> answer = Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!ping"))
                .flatMap(Message::getChannel)
                .flatMap(channel ->
                        channel.createMessage("Pong!"))
                .then();

        return answer;

    }

}