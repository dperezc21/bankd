package com.system.bankd.infrastructure.controllers;

import com.system.bankd.application.CardUseCases;
import com.system.bankd.domain.responses.AccountTransaction;
import com.system.bankd.domain.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CardController {

    @Autowired private CardUseCases cardUseCases;

    @PutMapping("/takeOut/card")
    public ResponseEntity takeOutWithCard(@RequestBody AccountTransaction transaction) {
        Double amount = 0.0;
        try {
            amount = this.cardUseCases.takeOutUsingCard(transaction.getAccountId(), transaction.getAmount());
        } catch(Exception e) {

        }

        return ResponseEntity.ok(new GenericResponse<>("", amount));
    }
}
