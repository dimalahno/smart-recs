package kz.smartrecs.authorization.repository;

import kz.smartrecs.authorization.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByEmailAndIsActive(String email, Boolean isActive);

    Customer findByEmail(String email);
}
