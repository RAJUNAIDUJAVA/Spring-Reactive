package com.example.reactive.handler;

import com.example.reactive.model.Customer;
import com.example.reactive.repo.CustomerDao;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    CustomerDao customerDao;

    public CustomerHandler(CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        System.out.println("request came to handler");
        Flux<Customer> customerList = customerDao.getCustomersList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> loadStream(ServerRequest request){
        System.out.println("request came to stream handler");
        Flux<Customer> customerFlux = customerDao.getCustomersStream();
        System.out.println("completed the mainthread");
        return ServerResponse.ok().body(customerFlux, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        int customerId= Integer.valueOf(request.pathVariable("input"));
        Mono<Customer> customeMono = customerDao.getCustomersList().filter(x-> x.getId()==customerId).next();
        return ServerResponse.ok().body(customeMono,Customer.class);
    }


    public Mono<ServerResponse>saveCustomer(ServerRequest request){
        Mono<Customer>customerMono =request.bodyToMono(Customer.class);
        Mono<String> response = customerMono.map(dto -> dto.getId() + ":"+ dto.getName());
        return ServerResponse.ok().body(response,String.class);
    }



}
