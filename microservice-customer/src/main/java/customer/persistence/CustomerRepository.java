package customer.persistence;

import customer.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Long> {
    Customer findByEmail(String email);
    List<Customer> findAllByStatusCustomer(Integer statusCustomer);

}
