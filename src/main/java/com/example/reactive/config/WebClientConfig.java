package com.example.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;
import reactor.netty.http.client.HttpClient;


import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        // created default uri with help of UriBuider
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory("http://localhost:8081/stream/call");


        return WebClient.builder()
                .uriBuilderFactory(factory)
                .clientConnector(new ReactorClientHttpConnector(callHttpClient())) // here we given HttpClient for read timeout
                .build();

    }

    public HttpClient callHttpClient(){
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(20)); // configured 20 secs read timeout
        return httpClient;
    }
}
