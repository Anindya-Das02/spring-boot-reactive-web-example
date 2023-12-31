package com.das.app.reactivedemo.service;

import com.das.app.reactivedemo.dto.ProductDto;
import com.das.app.reactivedemo.repository.ProductRepository;
import com.das.app.reactivedemo.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getAllProducts(){
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProductById(String id){
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> save2(ProductDto productDto){
        return productRepository.save(AppUtils.dtoToEntity(productDto))
                .map(AppUtils::entityToDto);
    }

    public Flux<ProductDto> getProductInPriceRange(double min, double max){
        return productRepository.findByPriceBetween(Range.closed(min,max)).map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id){
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtils::dtoToEntity))
                .doOnNext(e -> e.setId(id))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

}
