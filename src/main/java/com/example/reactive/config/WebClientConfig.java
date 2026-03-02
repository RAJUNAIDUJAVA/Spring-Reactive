package com.example.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){

        return WebClient.builder().baseUrl("http://localhost:8081")
                .clientConnector(new ReactorClientHttpConnector(callHttpClient())) // here we given HttpClient for read timeout
                .build();

    }

    public HttpClient callHttpClient(){
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(20)); // configured 20 secs read timeout
        return httpClient;
    }
}
