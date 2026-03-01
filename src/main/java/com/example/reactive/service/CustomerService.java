package com.example.reactive.service;

import com.example.reactive.model.Customer;
import com.example.reactive.repo.CustomerDao;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    CustomerDao customerDao;
    public CustomerService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }


    public List<Customer> loadAllCustomers(){
        long start = System.currentTimeMillis();
        System.out.println(start);
        List<Customer> customers = customerDao.getCustomers();
        long end=System.currentTimeMillis();
        System.out.println("total time taken is:"+ (end-start));
        return customers;
    }

    public Flux<Customer> loadAllCustomerStream() {
        long start = System.currentTimeMillis();

        Flux<Customer> customerFlux = customerDao.getCustomersStream();

        long end = System.currentTimeMillis();

        System.out.println("total time taken is:"+ (end-start));

        return customerFlux;
    }



}
