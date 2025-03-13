package com.Spring_Security_Back.product.repository;

import com.Spring_Security_Back.product.entities.entity.Product;
import com.Spring_Security_Back.product.entities.response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.Spring_Security_Back.product.entities.response.ProductResponse(p.idProduct, p.nameProduct, p.description, p.price, p.stock) FROM Product p")
    List<ProductResponse> findAllProduct();
}
