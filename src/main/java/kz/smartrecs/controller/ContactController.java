package kz.smartrecs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public String getBalanceDetails() {
        return "Inquire details are saved to the DB";
    }
}
