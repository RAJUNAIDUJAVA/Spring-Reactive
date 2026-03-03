package com.example.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;
import reactor.netty.http.client.HttpClient;


import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        // created default uri with help of UriBuider
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory("http://localhost:8081/stream/call/{message}");

        Map<String, Object> defaults= new HashMap<>();
        defaults.put("message", "Explain Java 8");
        // if i want to some default variables like path variable then i can use DefaultUrBuilderFactory
        factory.setDefaultUriVariables(defaults);

        return WebClient.builder()
                .uriBuilderFactory(factory)
                //.defaultHeader("xnmUid","x-nm-uid") // sending single header
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("xnmUid","x-nm-Uid"); // added multiple header
                    httpHeaders.set("xnmRequestId","12345"); // added multiple default headers
                })
                .clientConnector(new ReactorClientHttpConnector(callHttpClient())) // here we given HttpClient for read timeout
                .build();

    }

    public HttpHeaders httpHeaders(){
        HttpHeaders headers= new HttpHeaders();
        headers.put("xnmUid", List.of("x-nm-Uid"));
        return headers;
    }

    public HttpClient callHttpClient(){
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(20)); // configured 20 secs read timeout
        return httpClient;
    }
}
