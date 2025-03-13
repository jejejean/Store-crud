package com.Spring_Security_Back.order.models.request;

import com.Spring_Security_Back.details.models.request.OrderDetailRequest;
import com.Spring_Security_Back.shared.interfaces.IHandleRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest implements IHandleRequest {
    private Long idOrder;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
    private Double total;
    private Long client;
    private List<OrderDetailRequest> orderDetails;
}
