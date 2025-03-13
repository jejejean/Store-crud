package com.Spring_Security_Back.client.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long idClient;

    @NotNull(message = "El campo nombre de usuario no puede estar vacío")
    @Column(name = "username")
    private String userName;

    @NotNull(message = "El campo nombre no puede estar vacío")
    private String name;

    @NotNull(message = "El campo apellido no puede estar vacío")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "El campo email no es válido")
    @NotNull(message = "El email no puede estar vacío")
    private String email;

    @NotNull(message = "El campo teléfono no puede estar vacío")
    @Size(min = 9, max = 9, message = "El teléfono debe tener 9 dígitos")
    private String phone;

    @NotNull(message = "El campo DNI no puede estar vacío")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    private String dni;

    @NotNull(message = "El campo dirección no puede estar vacío")
    private String location;
}