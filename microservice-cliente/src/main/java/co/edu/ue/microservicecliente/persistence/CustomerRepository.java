package co.edu.ue.microservicecliente.persistence;

import co.edu.ue.microservicecliente.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
