package com.Spring_Security_Back.details.models.request;

import com.Spring_Security_Back.shared.interfaces.IHandleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest implements IHandleRequest {
    private Long idOrderDetail;
    private Long order;
    private Long product;
    private Integer quantity;
    private Double subTotal;
}
