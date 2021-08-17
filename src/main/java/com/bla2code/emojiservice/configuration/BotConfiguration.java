package com.bla2code.emojiservice.configuration;

import com.bla2code.emojiservice.events.EventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Log4j2
@Configuration
public class BotConfiguration {

    @Value("${token}")
    private String token;

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {

        GatewayDiscordClient client = null;

        try {

            client = DiscordClientBuilder.create(token).build().login().block();

            for(EventListener<T> listener : eventListeners) {
                assert client != null;
                client.on(listener.getEventType())
                        .flatMap(listener::execute)
                        .onErrorResume(listener::handleError)
                        .subscribe();
            }

        }catch (Exception exception) {
            log.error("Be sure to use a valid bot token!", exception);
        }

        return client;
    }

}
