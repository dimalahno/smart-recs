package kz.smartrecs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping("/myLoans")
    public String getBalanceDetails() {
        return "Here are loans details from the DB";
    }
}
