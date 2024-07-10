package kz.smartrecs.authorization.service;

import kz.smartrecs.authorization.entity.Customer;
import kz.smartrecs.authorization.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public boolean registerUserAccount(Customer customer) {
        if (isUserAccountPresent(customer)) {
            return customerRepository.save(customer).getCustomerId() > 0;
        } else {
            log.error("Error registering user account. UserAccount with email {} is present in database", customer.getEmail());
            throw new IllegalArgumentException("UserAccount with email " + customer.getEmail() + " is present in database");
        }
    }

    private boolean isUserAccountPresent(Customer customer) {
        Customer account = customerRepository.getUserAccountByEmail(customer.getEmail());
        return Objects.isNull(account);
    }
}
