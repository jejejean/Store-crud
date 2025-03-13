package com.Spring_Security_Back.details.models.mappper;

import com.Spring_Security_Back.details.models.entity.OrderDetail;
import com.Spring_Security_Back.details.models.request.OrderDetailRequest;
import com.Spring_Security_Back.details.models.response.OrderDetailResponse;
import com.Spring_Security_Back.order.models.entity.Order;
import com.Spring_Security_Back.product.entities.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailMapper {
    private final ModelMapper modelMapper;

    public OrderDetailMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.addMappings(new PropertyMap<OrderDetail, OrderDetailResponse>() {
            @Override
            protected void configure() {
                map(source.getProduct().getIdProduct(), destination.getProduct());
            }
        });
    }

    public OrderDetailResponse mapEntityToDto(OrderDetail orderDetail) {
        return modelMapper.map(orderDetail, OrderDetailResponse.class);
    }

    public OrderDetail mapDtoToEntity(OrderDetailRequest orderDetailRequest, Order order, Product product) {
        OrderDetail orderDetail = modelMapper.map(orderDetailRequest, OrderDetail.class);
        orderDetail.setIdOrderDetail(null);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        return orderDetail;
    }

    public List<OrderDetailResponse> mapListEntityToListDto(List<OrderDetail> orderDetailList) {
        return orderDetailList.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

}
