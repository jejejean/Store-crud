package com.Spring_Security_Back.product.service;

import com.Spring_Security_Back.exceptions.NotFoundException;
import com.Spring_Security_Back.product.entities.entity.Product;
import com.Spring_Security_Back.product.entities.mapper.ProductMapper;
import com.Spring_Security_Back.product.entities.request.ProductRequest;
import com.Spring_Security_Back.product.entities.response.ProductResponse;
import com.Spring_Security_Back.product.repository.ProductRepository;
import com.Spring_Security_Back.shared.constants.ExceptionMessages;
import com.Spring_Security_Back.shared.interfaces.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements CrudInterface<ProductRequest, ProductResponse> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAll() {
        if (productRepository.findAllProduct().isEmpty() || productRepository.findAllProduct() == null) {
            throw new NotFoundException(ExceptionMessages.PRODUCTS_NOT_FOUND);
        }
        return productRepository.findAllProduct();
    }

    @Override
    public Optional<ProductResponse> findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException(String.format(ExceptionMessages.PRODUCT_WITH_ID_NOT_FOUND, id));
        }
        return Optional.of(productMapper.mapEntityToDto(product.get()));
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        if (request == null) {
            throw new NotFoundException(ExceptionMessages.PRODUCT_NOT_NULL);
        }

        Product product = productMapper.mapDtoToEntity(request);
        Product productSaved = productRepository.save(product);
        return productMapper.mapEntityToDto(productSaved);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessages.PRODUCT_WITH_ID_NOT_FOUND, id)));
        product.setNameProduct(request.getNameProduct());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setDescription(request.getDescription());
        Product productSaved = productRepository.save(product);
        return productMapper.mapEntityToDto(productSaved);
    }

    @Override
    public String delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessages.PRODUCT_WITH_ID_NOT_FOUND, id)));

        productRepository.delete(product);
        return String.format(ExceptionMessages.PRODUCT_DELETED, id);
    }
}
