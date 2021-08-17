package com.bla2code.emojiservice.model;

import lombok.Getter;

@Getter
public enum Command {

    PING("!ping"),
    PONG("!pong");

    private final String description;

    Command(String description) {
        this.description = description;
    }
}
