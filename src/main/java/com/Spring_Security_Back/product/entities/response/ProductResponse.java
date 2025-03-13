package com.Spring_Security_Back.product.entities.response;

import com.Spring_Security_Back.shared.interfaces.IHandleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse implements IHandleResponse {
    private Long idProduct;
    private String nameProduct;
    private String description;
    private Double price;
    private Integer stock;
}
