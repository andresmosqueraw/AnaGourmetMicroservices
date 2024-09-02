package com.microservice.sale.persistence;

import com.microservice.sale.entities.Sale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {
    List<Sale> findByStatusSales(Long statusSales);
}
