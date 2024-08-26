package com.microservice.supplier.persistence;

import com.microservice.supplier.entities.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    // List<Inventory> findAllBySupplierId(Long supplierId); // esta linea iria para el repository de inventory

    // Mostrar todos los suppliers

}
