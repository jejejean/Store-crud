package com.Spring_Security_Back.order.service;

import com.Spring_Security_Back.client.models.entity.Client;
import com.Spring_Security_Back.client.repository.ClientRepository;
import com.Spring_Security_Back.details.models.entity.OrderDetail;
import com.Spring_Security_Back.details.models.mappper.OrderDetailMapper;
import com.Spring_Security_Back.details.models.request.OrderDetailRequest;
import com.Spring_Security_Back.exceptions.NotFoundException;
import com.Spring_Security_Back.order.models.entity.Order;
import com.Spring_Security_Back.order.models.mapper.OrderMapper;
import com.Spring_Security_Back.order.models.request.OrderRequest;
import com.Spring_Security_Back.order.models.response.OrderResponse;
import com.Spring_Security_Back.order.repository.OrderRepository;
import com.Spring_Security_Back.product.entities.entity.Product;
import com.Spring_Security_Back.product.repository.ProductRepository;
import com.Spring_Security_Back.shared.constants.ExceptionMessages;
import com.Spring_Security_Back.shared.interfaces.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements CrudInterface<OrderRequest, OrderResponse> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderResponse> findAll() {
        if (orderRepository.findAllOrder().isEmpty() || orderRepository.findAllOrder() == null) {
            throw new NotFoundException(ExceptionMessages.ORDERS_NOT_FOUND);
        }
        return orderRepository.findAllOrder();
    }

    @Override
    public Optional<OrderResponse> findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new NotFoundException(String.format(ExceptionMessages.ORDER_WITH_ID_NOT_FOUND, id));
        }
        return Optional.of(orderMapper.mapEntityToDto(order.get()));
    }

    @Override
    public OrderResponse create(OrderRequest request) {
        if (request == null) {
            throw new NotFoundException(ExceptionMessages.ORDER_NOT_NULL);
        }

        Client client = clientRepository.findById(request.getClient())
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.CLIENT_WITH_ID_NOT_FOUND));

        Order order = orderMapper.mapDtoToEntity(request, client);
        Order orderSaved = orderRepository.save(order);
        return orderMapper.mapEntityToDto(orderSaved);
    }

    @Override
    public OrderResponse update(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessages.ORDER_WITH_ID_NOT_FOUND, id)));

        Client client = clientRepository.findById(orderRequest.getClient())
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.CLIENT_WITH_ID_NOT_FOUND));

        order.setClient(client);
        order.setDate(orderRequest.getDate());
        order.setTotal(orderRequest.getTotal());

        List<OrderDetail> updatedOrderDetails = new ArrayList<>();

        if (orderRequest.getOrderDetails() != null && !orderRequest.getOrderDetails().isEmpty()) {
            for (OrderDetailRequest orderDetailRequest : orderRequest.getOrderDetails()) {
                // Buscar el producto correspondiente al ID del producto
                Product product = productRepository.findById(orderDetailRequest.getProduct())
                        .orElseThrow(() -> new RuntimeException(ExceptionMessages.PRODUCT_WITH_ID_NOT_FOUND));

                // Crear o actualizar los detalles de la orden (OrderDetail)
                OrderDetail orderDetail = orderDetailMapper.mapDtoToEntity(orderDetailRequest, order, product);

                // Añadir los detalles de la orden a la lista
                updatedOrderDetails.add(orderDetail);
            }
        }

        order.setOrderDetails(updatedOrderDetails);

        Order updateOrder = orderRepository.save(order);
        return orderMapper.mapEntityToDto(updateOrder);
    }

    @Override
    public String delete(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessages.ORDER_WITH_ID_NOT_FOUND, id)));

        orderRepository.delete(order);
        return String.format(ExceptionMessages.ORDER_DELETED, id);
    }
}
