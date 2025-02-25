package com.system.bankd.infrastructure.controllers;

import com.system.bankd.application.UserAccountUseCases;
import com.system.bankd.domain.responses.AccountDeposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class UserAccountController {

    @Autowired private UserAccountUseCases userAccountUseCases;

    @PutMapping("/deposit")
    public ResponseEntity<AccountDeposit> depositInAccount(@RequestBody AccountDeposit accountDeposit) {
        AccountDeposit accountUpdated = null;
        try {
            accountUpdated = this.userAccountUseCases.accountDeposit(accountDeposit.getUserId(), accountDeposit.getAccountId(), accountDeposit.getAmount());
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok(accountUpdated);
    }
}
