package com.microservice.customer.persistence;

import com.microservice.customer.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Long> {
}
