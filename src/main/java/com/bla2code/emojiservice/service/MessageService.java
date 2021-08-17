package com.bla2code.emojiservice.service;

import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface MessageService {
    Mono<Void> processCommand(Message eventMessage);
}
