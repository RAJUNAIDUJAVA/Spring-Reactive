package com.example.reactive.router;

import com.example.reactive.handler.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CustomerRouter {
    @Autowired
    private CustomerHandler customerHandler;


    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers",customerHandler::loadCustomers)
                .GET("/router/stream",customerHandler::loadStream)
                .GET("/router/stream/{input}",customerHandler::findCustomer)
                .POST("/router/customer/save",customerHandler::saveCustomer)
                .build();
    }

}
