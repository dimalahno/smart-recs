package kz.smartrecs.authorization.repository;

import kz.smartrecs.authorization.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findByEmailAndIsActive(String email, Boolean isActive);

    Customer getUserAccountByEmail(String email);

    Customer findUserAccountByEmailAndIsActive(String email, Boolean isActive);
}
