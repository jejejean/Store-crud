package com.Spring_Security_Back.order.models.entity;

import com.Spring_Security_Back.client.models.entity.Client;
import com.Spring_Security_Back.details.models.entity.OrderDetail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @NotNull(message = "El campo fecha no puede estar vacío")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull(message = "El campo total no puede estar vacío")
    private Double total;

    @NotNull(message = "El campo cliente no puede estar vacío")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
