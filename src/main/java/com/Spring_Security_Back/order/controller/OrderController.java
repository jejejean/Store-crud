package com.Spring_Security_Back.order.controller;

import com.Spring_Security_Back.order.models.request.OrderRequest;
import com.Spring_Security_Back.order.models.response.OrderResponse;
import com.Spring_Security_Back.shared.interfaces.CrudInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final CrudInterface<OrderRequest, OrderResponse> crudInterface;

    public OrderController(CrudInterface<OrderRequest, OrderResponse> crudInterface) {
        this.crudInterface = crudInterface;
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(crudInterface.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<OrderResponse> orderResponse = crudInterface.findById(id);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping("/new-order")
    public ResponseEntity<Object> create(@Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = crudInterface.create(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-order/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = crudInterface.update(id, orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        crudInterface.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
