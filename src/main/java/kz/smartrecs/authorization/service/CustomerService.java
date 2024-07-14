package kz.smartrecs.authorization.service;

import kz.smartrecs.authorization.model.Customer;
import kz.smartrecs.authorization.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public boolean registerUserAccount(final Customer customer) {
        if (isUserAccountPresent(customer)) {
            customer.setCreateDt(new Date());
            return customerRepository.save(customer).getCustomerId() > 0;
        } else {
            log.error("Error registering user account. UserAccount with email {} is present in database", customer.getEmail());
            throw new IllegalArgumentException("UserAccount with email " + customer.getEmail() + " is present in database");
        }
    }

    // TODO переделать с учётом активности аккаунта
    private boolean isUserAccountPresent(final Customer customer) {
        Customer account = customerRepository.findByEmail(customer.getEmail());
        return Objects.isNull(account);
    }
}
