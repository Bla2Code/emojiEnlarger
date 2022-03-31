package com.bla2code.emojiservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class EmojiServiceApplication {

    public static void main(String[] args) {
        //Start spring application
        new SpringApplicationBuilder(EmojiServiceApplication.class)
                .build()
                .run(args);
    }
}
