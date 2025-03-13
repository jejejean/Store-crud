package com.Spring_Security_Back.client.models.mapper;

import com.Spring_Security_Back.client.models.entity.Client;
import com.Spring_Security_Back.client.models.request.ClientRequest;
import com.Spring_Security_Back.client.models.response.ClientResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    private final ModelMapper modelMapper;

    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClientResponse mapEntityToDto(Client client) {
        return modelMapper.map(client, ClientResponse.class);
    }

    public Client mapDtoToEntity(ClientRequest clientRequest) {
        Client client = modelMapper.map(clientRequest, Client.class);
        client.setIdClient(null);
        return client;
    }
}
