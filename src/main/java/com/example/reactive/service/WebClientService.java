package com.example.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilderFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {
    @Autowired
    WebClient webClient;

    public Mono<String> makeCall(){

        return webClient.get()
                .uri("/call/{message}")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Flux<String> makecall2(String message){

        return webClient.get()
                .uri("/call2/{message}",message)
                .retrieve()
                .bodyToFlux(String.class);
    }
}
