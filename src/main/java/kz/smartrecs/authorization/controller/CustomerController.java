package kz.smartrecs.authorization.controller;

import jakarta.validation.Valid;
import kz.smartrecs.authorization.entity.Customer;

import kz.smartrecs.authorization.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

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

}
