package com.bla2code.emojiservice.events.service;

import discord4j.core.event.domain.message.MessageUpdateEvent;
import com.bla2code.emojiservice.events.EventListener;
import com.bla2code.emojiservice.events.MessageListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageUpdateListener extends MessageListener implements EventListener<MessageUpdateEvent> {

    @Override
    public Class<MessageUpdateEvent> getEventType() {
        return MessageUpdateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageUpdateEvent event) {
        return Mono.just(event)
           .filter(MessageUpdateEvent::isContentChanged)
           .flatMap(MessageUpdateEvent::getMessage)
           .flatMap(super::processCommand);
    }
}