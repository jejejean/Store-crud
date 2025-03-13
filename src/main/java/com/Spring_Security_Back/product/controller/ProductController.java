package com.Spring_Security_Back.product.controller;

import com.Spring_Security_Back.product.entities.request.ProductRequest;
import com.Spring_Security_Back.product.entities.response.ProductResponse;
import com.Spring_Security_Back.shared.interfaces.CrudInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final CrudInterface<ProductRequest, ProductResponse> crudInterface;

    public ProductController(CrudInterface<ProductRequest, ProductResponse> crudInterface) {
        this.crudInterface = crudInterface;
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(crudInterface.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<ProductResponse> productResponse = crudInterface.findById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PostMapping("/new-product")
    public ResponseEntity<Object> create(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = crudInterface.create(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = crudInterface.update(id, productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        crudInterface.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
