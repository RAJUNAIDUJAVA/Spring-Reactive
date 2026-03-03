package com.example.reactive.controller;

import com.example.reactive.model.Customer;
import com.example.reactive.service.CustomerService;
import com.example.reactive.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private WebClientService webClientservice;


    @GetMapping("/list")
    public List<Customer>getAllCustomers(){
        return customerService.loadAllCustomers();
    }

    /*
    MediaType.TEXT_EVENT_STREAM_VALUE will help us share the output as a stream
    like what ever it get from the downsteam or method it will immediately will given to the consumer
    */
    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)

    public Flux<Customer> getAllCustomersStream(){

        return customerService.loadAllCustomerStream();
    }
    @GetMapping("call")
    public Mono<String> getResultFromWebClient(){
        return webClientservice.makeCall();
    }

    @GetMapping("call2/{message}")
    public Flux<String> getResultFromWebClient(@PathVariable String message){
        return webClientservice.makecall2(message);
    }


}
