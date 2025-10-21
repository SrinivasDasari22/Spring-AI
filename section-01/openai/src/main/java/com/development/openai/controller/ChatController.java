package com.development.openai.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message){

        return chatClient
                .prompt()
                .options(OpenAiChatOptions.builder().model(OpenAiApi.ChatModel.CHATGPT_4_O_LATEST).build())
                //.system("you are python architect, apart from the Python related question , don't give response to any other queries\"")
                .user(message)
                .call().content();
    }
}
