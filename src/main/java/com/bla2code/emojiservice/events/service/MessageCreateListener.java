package com.bla2code.emojiservice.events.service;

import discord4j.core.event.domain.message.MessageCreateEvent;
import com.bla2code.emojiservice.events.EventListener;
import com.bla2code.emojiservice.events.MessageListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListener extends MessageListener implements EventListener<MessageCreateEvent> {

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return processCommand(event.getMessage());
    }
}