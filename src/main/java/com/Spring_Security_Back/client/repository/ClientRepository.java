package com.Spring_Security_Back.client.repository;

import com.Spring_Security_Back.client.models.entity.Client;
import com.Spring_Security_Back.client.models.response.ClientResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsUserEntityByEmail(String email);

    @Query("SELECT new com.Spring_Security_Back.client.models.response.ClientResponse(c.idClient, c.userName, c.name, c.lastName, c.email, c.phone, c.dni, c.location) FROM Client c")
    List<ClientResponse> findAllClient();

    Client findByEmail(String email);

}
