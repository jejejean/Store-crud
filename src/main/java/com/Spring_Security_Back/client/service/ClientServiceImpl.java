package com.Spring_Security_Back.client.service;

import com.Spring_Security_Back.client.models.entity.Client;
import com.Spring_Security_Back.client.models.mapper.ClientMapper;
import com.Spring_Security_Back.client.models.request.ClientRequest;
import com.Spring_Security_Back.client.models.response.ClientResponse;
import com.Spring_Security_Back.client.repository.ClientRepository;
import com.Spring_Security_Back.exceptions.BadRequestException;
import com.Spring_Security_Back.exceptions.NotFoundException;
import com.Spring_Security_Back.shared.constants.ExceptionMessages;
import com.Spring_Security_Back.shared.interfaces.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements CrudInterface<ClientRequest, ClientResponse> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientResponse> findAll() {
        if (clientRepository.findAllClient().isEmpty() || clientRepository.findAllClient() == null) {
            throw new NotFoundException(ExceptionMessages.CLIENTS_NOT_FOUND);
        }
        return clientRepository.findAllClient();
    }

    @Override
    public Optional<ClientResponse> findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new NotFoundException(String.format(ExceptionMessages.CLIENT_WITH_ID_NOT_FOUND, id));
        }
        return Optional.of(clientMapper.mapEntityToDto(client.get()));
    }

    @Override
    public ClientResponse create(ClientRequest clientRequest) {
        if (clientRequest == null) {
            throw new NotFoundException(ExceptionMessages.CLIENT_NOT_NULL);
        }

        if (clientRepository.existsUserEntityByEmail(clientRequest.getEmail())) {
            throw new NotFoundException("Email already exists");
        }

        Client client = clientMapper.mapDtoToEntity(clientRequest);
        Client clientSaved = clientRepository.save(client);

        return clientMapper.mapEntityToDto(clientSaved);
    }

    @Override
    public ClientResponse update(Long id, ClientRequest clientRequest) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException(ExceptionMessages.CLIENT_WITH_ID_NOT_FOUND));

        Client clientWithSameEmail = clientRepository.findByEmail(clientRequest.getEmail());

        if (clientWithSameEmail != null && !clientWithSameEmail.getIdClient().equals(client.getIdClient())) {
            throw new BadRequestException(String.format(ExceptionMessages.EMAIL_ALREADY_REGISTERED, clientRequest.getEmail()));
        }
        client.setEmail(clientRequest.getEmail());
        client.setDni(clientRequest.getDni());
        client.setName(clientRequest.getName());
        client.setLastName(clientRequest.getLastName());
        client.setPhone(clientRequest.getPhone());
        client.setUserName(clientRequest.getUserName());
        client.setLocation(clientRequest.getLocation());

        Client updateClient = clientRepository.save(client);

        return clientMapper.mapEntityToDto(updateClient);
    }

    @Override
    public String delete(Long id) {

        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessages.CLIENT_WITH_ID_NOT_FOUND, id)));

        clientRepository.delete(existingClient);

        return String.format(ExceptionMessages.CLIENT_DELETED, id);
    }
}
