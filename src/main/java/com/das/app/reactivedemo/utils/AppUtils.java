package com.das.app.reactivedemo.utils;

import com.das.app.reactivedemo.dto.ProductDto;
import com.das.app.reactivedemo.entity.Product;
import org.springframework.beans.BeanUtils;

public interface AppUtils {

    static ProductDto entityToDto(Product productEntity){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productEntity,productDto);
        return productDto;
    }

    static Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }
}
