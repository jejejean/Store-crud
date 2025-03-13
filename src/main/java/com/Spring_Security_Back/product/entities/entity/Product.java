package com.Spring_Security_Back.product.entities.entity;


import com.Spring_Security_Back.details.models.entity.OrderDetail;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @NotNull(message = "El campo nombre no puede estar vacío")
    @Column(name = "name_product")
    private String nameProduct;

    @NotNull(message = "El campo descripción no puede estar vacío")
    private String description;

    @NotNull(message = "El campo precio no puede estar vacío")
    @Min(value = 1, message = "El precio no puede ser menor a 1")
    private Double price;

    @NotNull(message = "El campo stock no puede estar vacío")
    @Min(value = 1, message = "El stock no puede ser menor a 1")
    private Integer stock;


}
