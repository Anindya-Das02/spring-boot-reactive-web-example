package com.das.app.reactivedemo.config;

import com.das.app.reactivedemo.handler.RouterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ReactiveAppConfig {

    @Autowired
    private RouterHandler routerHandler;

    @Bean
    public RouterFunction<ServerResponse> routeConfig(){
        return RouterFunctions.route()
                .GET("/api/products/all",routerHandler::handleGetAllProducts)
                .GET("/api/products2/all",routerHandler::handleGetAllProducts2)
                .GET("/api/products/{id}",routerHandler::handleProductById)
                .POST("/api/products/save",routerHandler::handleSaveProduct)
                .build();
    }
}
