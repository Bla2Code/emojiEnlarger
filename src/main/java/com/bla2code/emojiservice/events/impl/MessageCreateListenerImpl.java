package com.bla2code.emojiservice.events.impl;

import com.bla2code.emojiservice.events.EventListener;
import com.bla2code.emojiservice.service.MessageService;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListenerImpl implements EventListener<MessageCreateEvent> {

    @Qualifier("ping")
    private final MessageService messageService;

    public MessageCreateListenerImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return messageService.processCommand(event.getMessage());
    }
}