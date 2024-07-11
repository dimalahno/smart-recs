package kz.smartrecs.authorization.controller;

import jakarta.validation.Valid;
import kz.smartrecs.authorization.model.Customer;

import kz.smartrecs.authorization.repository.CustomerRepository;
import kz.smartrecs.authorization.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;


    @PostMapping("/register")
    public ResponseEntity<String> registerUserAccount(@Valid @RequestBody Customer customer) {
        ResponseEntity<String> response = null;
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            if (customerService.registerUserAccount(customer)) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("New user account registered successfully");

            }
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred while registering new user account: " + e.getMessage());
        }

        return response;
    }

    /**
     * Возврат активного пользователя
     * @param authentication параметры аутентификации
     * @return Данные не заблокированного пользователя
     */
    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        return customerRepository.findByEmailAndIsActive(authentication.getName(), true);
    }
}
