package com.Spring_Security_Back.order.repository;

import com.Spring_Security_Back.order.models.entity.Order;
import com.Spring_Security_Back.order.models.response.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT new com.Spring_Security_Back.order.models.response.OrderResponse(o.idOrder, o.date, o.total, o.client.idClient, " +
            "(SELECT new com.Spring_Security_Back.details.models.response.OrderDetailResponse(od.idOrderDetail, od.order.idOrder, od.product.idProduct, od.quantity, od.subTotal) " +
            " FROM OrderDetail od WHERE od.order.idOrder = o.idOrder)) " +
            "FROM Order o")
    List<OrderResponse> findAllOrder();

}
