package com.bla2code.emojiservice.events.impl;

import com.bla2code.emojiservice.events.EventListener;
import com.bla2code.emojiservice.service.MessageService;
import discord4j.core.event.domain.message.MessageUpdateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageUpdateListenerImpl implements EventListener<MessageUpdateEvent> {

    private final MessageService messageService;

    public MessageUpdateListenerImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public Class<MessageUpdateEvent> getEventType() {
        return MessageUpdateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageUpdateEvent event) {
        return Mono.just(event)
                .filter(MessageUpdateEvent::isContentChanged)
                .flatMap(MessageUpdateEvent::getMessage)
                .flatMap(messageService::processCommand);
    }
}