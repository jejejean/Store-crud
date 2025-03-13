package com.Spring_Security_Back.details.models.response;

import com.Spring_Security_Back.shared.interfaces.IHandleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse implements IHandleResponse {
    private Long idOrderDetail;
    private Long order;
    private Long product;
    private Integer quantity;
    private Double subTotal;
}
