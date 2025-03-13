package com.Spring_Security_Back.product.entities.request;

import com.Spring_Security_Back.shared.interfaces.IHandleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest implements IHandleRequest {
    private Long idProduct;
    private String nameProduct;
    private String description;
    private Double price;
    private Integer stock;
}
