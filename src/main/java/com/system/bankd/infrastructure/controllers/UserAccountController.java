package com.system.bankd.infrastructure.controllers;

import com.system.bankd.application.UserAccountUseCases;
import com.system.bankd.domain.responses.AccountTransaction;
import com.system.bankd.domain.responses.GenericResponse;
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
    public ResponseEntity depositInAccount(@RequestBody AccountTransaction accountTransaction) {
        AccountTransaction accountUpdated = null;
        try {
            accountUpdated = this.userAccountUseCases.accountDeposit(accountTransaction.getUserId(), accountTransaction.getAccountId(), accountTransaction.getAmount());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponse(e.getMessage(), null));
        }
        return ResponseEntity.ok(new GenericResponse("", accountUpdated));
    }

    @PutMapping("/takeOut")
    public ResponseEntity withdrawInAccount(@RequestBody AccountTransaction accountTransaction) {
        AccountTransaction accountUpdated = null;
        try {
            accountUpdated = this.userAccountUseCases.withdrawInUserAccount(accountTransaction.getUserId(), accountTransaction.getAccountId(), accountTransaction.getAmount());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponse(e.getMessage(), null));
        }
        return ResponseEntity.ok(new GenericResponse("take out successfully", accountUpdated));
    }
}
