package com.Spring_Security_Back.client.models.response;

import com.Spring_Security_Back.shared.interfaces.IHandleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse implements IHandleResponse {
    private Long idClient;
    private String userName;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String dni;
    private String location;
}
