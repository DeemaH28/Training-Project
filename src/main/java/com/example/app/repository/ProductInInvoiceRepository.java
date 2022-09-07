package com.example.app.repository;

import com.example.app.model.ProductInInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInInvoiceRepository extends JpaRepository<ProductInInvoice,Integer> {

    Long countById(Integer id);
}
