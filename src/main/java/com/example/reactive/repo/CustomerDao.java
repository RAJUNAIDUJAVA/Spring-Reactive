package com.example.reactive.repo;

import com.example.reactive.model.Customer;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {


    private static void sleepExecution(int i) {
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException exception){
            System.out.println(exception.getStackTrace());
        }
    }


    public List<Customer> getCustomers(){
        return IntStream.rangeClosed(1,10).peek(CustomerDao::sleepExecution)
                .peek(i-> System.out.println("Processing count : "+i))
                .mapToObj(i-> new Customer(i,"Cusomer"+i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream(){
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(10))
                .doOnNext(i-> System.out.println("processing count in stream flow :"+i))
                .map(i-> new Customer(i, "Customer"+i));
    }

    public Flux<Customer> getCustomersList(){
        return Flux.range(1,50)
                .doOnNext(i-> System.out.println("processing count in stream flow :"+i))
                .map(i-> new Customer(i, "Customer"+i));
    }










}
