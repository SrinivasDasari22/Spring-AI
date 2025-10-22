package com.development.openai.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class StreamController {

    private final ChatClient chatClient;

    public StreamController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/stream")
    public Flux<String> stream(@RequestParam("message") String message){

        return chatClient
                .prompt()
                //.system("you are python architect, apart from the Python related question , don't give response to any other queries\"")
                .user(message)
                .stream().content();
    }

}
