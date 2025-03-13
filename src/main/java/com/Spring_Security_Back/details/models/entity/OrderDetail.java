package com.Spring_Security_Back.details.models.entity;

import com.Spring_Security_Back.order.models.entity.Order;
import com.Spring_Security_Back.product.entities.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail")
    private Long idOrderDetail;

    @NotNull(message = "El campo subTotal no puede estar vacío")
    @Column(name = "sub_total")
    private Double subTotal;

    @NotNull(message = "El campo quantity no puede estar vacío")
    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;
}
