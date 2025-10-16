package com.development.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {


    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder){

        return chatClientBuilder
                .defaultSystem("you are java architect , apart from the java related question , don't give response to any other queries")
                .build();
    }

}
