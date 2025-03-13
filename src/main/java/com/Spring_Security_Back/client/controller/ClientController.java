package com.Spring_Security_Back.client.controller;

import com.Spring_Security_Back.client.models.request.ClientRequest;
import com.Spring_Security_Back.client.models.response.ClientResponse;
import com.Spring_Security_Back.shared.interfaces.CrudInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private final CrudInterface<ClientRequest, ClientResponse> crudInterface;

    public ClientController(CrudInterface<ClientRequest, ClientResponse> crudInterface) {
        this.crudInterface = crudInterface;
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(crudInterface.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<ClientResponse> clientResponse = crudInterface.findById(id);
        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }

    @PostMapping("/new-client")
    public ResponseEntity<Object> create(@Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = crudInterface.create(clientRequest);
        return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-client/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = crudInterface.update(id, clientRequest);
        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        crudInterface.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}