package com.example.demo.jsf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Component
public class CallService {
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);

    private final WebClient localApiClient;

    @Autowired
    public CallService(WebClient.Builder webClientBuilder) {
        this.localApiClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public String callSayHello(String name) {
        return localApiClient
                .get()
                .uri("/hello", uri -> uri.queryParam("name", name).build())
                .retrieve()
                .bodyToMono(String.class)
                .block(REQUEST_TIMEOUT);
    }
}
