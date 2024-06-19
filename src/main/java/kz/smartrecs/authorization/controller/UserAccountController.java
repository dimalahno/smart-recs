package kz.smartrecs.authorization.controller;

import jakarta.validation.Valid;
import kz.smartrecs.authorization.entity.UserAccount;

import kz.smartrecs.authorization.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user-account")
@RequiredArgsConstructor
@Slf4j
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUserAccount(@Valid @RequestBody UserAccount userAccount) {
        ResponseEntity<String> response = null;
        try {
            if (userAccountService.registerUserAccount(userAccount)) {
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
