package com.Spring_Security_Back.product.entities.mapper;

import com.Spring_Security_Back.product.entities.entity.Product;
import com.Spring_Security_Back.product.entities.request.ProductRequest;
import com.Spring_Security_Back.product.entities.response.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductResponse mapEntityToDto(Product product) {
        return modelMapper.map(product, ProductResponse.class);
    }

    public Product mapDtoToEntity(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        product.setIdProduct(null);
        return product;
    }
}
