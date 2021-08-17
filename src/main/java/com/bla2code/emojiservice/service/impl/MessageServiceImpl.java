package com.bla2code.emojiservice.service.impl;

import com.bla2code.emojiservice.service.CommandService;
import com.bla2code.emojiservice.service.MessageService;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {

    private final Set<CommandService> commandServices;

    public MessageServiceImpl(Set<CommandService> commandServices) {
        this.commandServices = commandServices;
    }

    @Override
    public Mono<Void> checkCommand(Message eventMessage) {

        var answer =  Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!ping"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Pong!")).then()
                ;

//        var answer = Mono.just(eventMessage)
//                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
//                .mapNotNull(commandMessage -> {
//                            String command = commandMessage.getContent();
//
////                            commandServices.forEach(process -> {
////                                if (process instanceof PingServiceImpl) {
////                                    process.processCommand(commandMessage);
////                                }
////                            });
//                            return null;
//                        }
//                );

        return answer;

    }

}