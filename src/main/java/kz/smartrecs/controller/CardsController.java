package kz.smartrecs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/myCards")
    public String getBalanceDetails() {
        return "Here are cards details from the DB";
    }
}
