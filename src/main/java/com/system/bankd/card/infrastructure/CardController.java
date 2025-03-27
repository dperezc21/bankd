package com.system.bankd.card.infrastructure;

import com.system.bankd.card.application.CardUseCases;
import com.system.bankd.account.domain.AccountTransactionResponse;
import com.system.bankd.GenericResponse;
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
    public ResponseEntity takeOutWithCard(@RequestBody AccountTransactionResponse transaction) {
        Double amount = 0.0;
        try {
            amount = this.cardUseCases.takeOutUsingCard(transaction.getAccountId(), transaction.getAmount());
        } catch(Exception e) {

        }

        return ResponseEntity.ok(new GenericResponse<>("", amount));
    }
}
