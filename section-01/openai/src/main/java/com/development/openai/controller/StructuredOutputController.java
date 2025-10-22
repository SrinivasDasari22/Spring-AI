package com.development.openai.controller;


import com.development.openai.model.CountryAndCities;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StructuredOutputController {

    private final ChatClient chatClient;

    public StructuredOutputController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/chat-bean")
    public ResponseEntity<CountryAndCities> chatBean(@RequestParam("message") String message){

        CountryAndCities countryAndCities= chatClient
                .prompt()
                //.system("you are python architect, apart from the Python related question , don't give response to any other queries\"")
                .user(message)
                .call()
                //.entity(CountryAndCities.class);
                .entity(new BeanOutputConverter<>(CountryAndCities.class));

        return ResponseEntity.ok(countryAndCities);
    }

    @GetMapping("/chat-list")
    public ResponseEntity<List<String>> chatList(@RequestParam("message") String message){

        List<String> countryAndCities = chatClient
                .prompt()
                .user(message)
                .call().entity(new ListOutputConverter());

        return ResponseEntity.ok(countryAndCities);
    }

    @GetMapping("/chat-map")
    public ResponseEntity<Map<String,Object>> chatMap(@RequestParam("message") String message){

        Map<String,Object> countryAndCities = chatClient
                .prompt()
                .user(message)
                .call().entity(new MapOutputConverter());

        return ResponseEntity.ok(countryAndCities);
    }

    @GetMapping("/chat-map-bean")
    public ResponseEntity<Map<String,List<String>>> chatMapBean(@RequestParam("message") String message){

        Map<String,List<String>> countryAndCities = chatClient
                .prompt()
                .user(message)
                .call().entity(new ParameterizedTypeReference<Map<String, List<String>>>() {
                });

        return ResponseEntity.ok(countryAndCities);
    }

    @GetMapping("/chat-bean-list")
    public ResponseEntity<List<CountryAndCities>> chatBeanList(@RequestParam("message") String message){

        List<CountryAndCities> countryAndCities = chatClient
                .prompt()
                .user(message)
                .call().entity(new ParameterizedTypeReference<List<CountryAndCities>>() {
                });

        return ResponseEntity.ok(countryAndCities);
    }

}
