package com.Spring_Security_Back.order.models.response;

import com.Spring_Security_Back.details.models.response.OrderDetailResponse;
import com.Spring_Security_Back.shared.interfaces.IHandleResponse;
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
public class OrderResponse implements IHandleResponse {
    private Long idOrder;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
    private Double total;
    private Long client;
    private List<OrderDetailResponse> orderDetails;
}
