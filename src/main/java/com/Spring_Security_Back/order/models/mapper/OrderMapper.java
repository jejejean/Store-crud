package com.Spring_Security_Back.order.models.mapper;

import com.Spring_Security_Back.client.models.entity.Client;
import com.Spring_Security_Back.details.models.entity.OrderDetail;
import com.Spring_Security_Back.details.models.mappper.OrderDetailMapper;
import com.Spring_Security_Back.details.models.response.OrderDetailResponse;
import com.Spring_Security_Back.order.models.entity.Order;
import com.Spring_Security_Back.order.models.request.OrderRequest;
import com.Spring_Security_Back.order.models.response.OrderResponse;
import com.Spring_Security_Back.product.entities.entity.Product;
import com.Spring_Security_Back.product.repository.ProductRepository;
import com.Spring_Security_Back.shared.constants.ExceptionMessages;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    private final ModelMapper modelMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final ProductRepository productRepository;

    public OrderMapper(ModelMapper modelMapper, OrderDetailMapper orderDetailMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.productRepository = productRepository;
    }

    public OrderResponse mapEntityToDto(Order order) {
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        List<OrderDetailResponse> orderDetailResponse = orderDetailMapper.mapListEntityToListDto(order.getOrderDetails());
        orderResponse.setOrderDetails(orderDetailResponse);
        return orderResponse;
    }

    public Order mapDtoToEntity(OrderRequest orderRequest, Client client) {
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setIdOrder(null);
        order.setClient(client);

        if (orderRequest.getOrderDetails() != null && !orderRequest.getOrderDetails().isEmpty()) {
            List<OrderDetail> orderDetails = orderRequest.getOrderDetails().stream()
                    .map(orderDetailRequest -> {
                        Product product = productRepository.findById(orderDetailRequest.getProduct())
                                .orElseThrow(() -> new RuntimeException(ExceptionMessages.PRODUCT_WITH_ID_NOT_FOUND));
                        return orderDetailMapper.mapDtoToEntity(orderDetailRequest, order, product);
                    })
                    .toList();
            order.setOrderDetails(orderDetails);
        }
        return order;
    }
}
