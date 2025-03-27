package com.system.bankd.account.infrastructure;

import com.system.bankd.account.application.UserAccountUseCases;
import com.system.bankd.user.application.UserUseCases;
import com.system.bankd.account.domain.AccountType;
import com.system.bankd.account.domain.AccountTransactionResponse;
import com.system.bankd.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class UserAccountController {

    @Autowired private UserAccountUseCases userAccountUseCases;
    @Autowired private UserUseCases userUseCases;

    @PutMapping("/deposit")
    public ResponseEntity depositInAccount(@RequestBody AccountTransactionResponse accountTransactionResponse) {
        AccountTransactionResponse accountUpdated = null;
        try {
            accountUpdated = this.userAccountUseCases.accountDeposit(accountTransactionResponse.getUserId(), accountTransactionResponse.getAccountId(), accountTransactionResponse.getAmount());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponse(e.getMessage(), null));
        }
        return ResponseEntity.ok(new GenericResponse("", accountUpdated));
    }

    @PutMapping("/takeOut")
    public ResponseEntity withdrawInAccount(@RequestBody AccountTransactionResponse accountTransactionResponse) {
        AccountTransactionResponse accountUpdated = null;
        try {
            accountUpdated = this.userAccountUseCases.withdrawInUserAccount(accountTransactionResponse.getUserId(), accountTransactionResponse.getAccountId(), accountTransactionResponse.getAmount());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponse(e.getMessage(), null));
        }
        return ResponseEntity.ok(new GenericResponse("take out successfully", accountUpdated));
    }

    @PostMapping
    public ResponseEntity saveUserAccount(@RequestBody AccountTransactionResponse accountTransactionResponse) {
        AccountTransactionResponse accountSaved = null;
        try {
            AccountType accountType = AccountType.valueOf(accountTransactionResponse.getAccountType().toUpperCase());
            accountSaved = this.userAccountUseCases.saveUserAccount(accountType, accountTransactionResponse.getUserId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponse(e.getMessage(), null));
        }
        return ResponseEntity.ok(new GenericResponse("account saved", accountSaved));
    }
}
