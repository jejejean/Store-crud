package com.Spring_Security_Back.client.models.request;

import com.Spring_Security_Back.shared.interfaces.IHandleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest implements IHandleRequest {
    private Long idClient;
    private String userName;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String dni;
    private String location;
}
