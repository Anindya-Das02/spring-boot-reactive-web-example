package com.das.app.reactivedemo.handler;

import com.das.app.reactivedemo.dto.ProductDto;
import com.das.app.reactivedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RouterHandler {

    @Autowired
    private ProductService productService;

    private static final MediaType TEXT_EVENT_STREAM = MediaType.TEXT_EVENT_STREAM;

    public Mono<ServerResponse> handleGetAllProducts(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(productService.getAllProducts(), ProductDto.class);
    }

    public Mono<ServerResponse> handleProductById(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(productService.getProductById(serverRequest.pathVariable("id")), ProductDto.class);
    }

    public Mono<ServerResponse> handleGetAllProducts2(ServerRequest serverRequest){
        return ServerResponse.ok()
                .body(productService.getAllProducts(), ProductDto.class);
    }

    public Mono<ServerResponse> handleSaveProduct(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(productService.saveProduct(serverRequest.bodyToMono(ProductDto.class)), ProductDto.class);
    }
}
