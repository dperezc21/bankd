package com.system.bankd.infrastructure.controllers;

import com.system.bankd.application.MovementUseCase;
import com.system.bankd.domain.responses.GenericResponse;
import com.system.bankd.domain.responses.MovementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movements")
public class AccountMovementController {

    @Autowired private MovementUseCase movementUseCase;

    @GetMapping("/all/{accountId}")
    public ResponseEntity getMovementsOfAccount(@PathVariable Long accountId) {
        List<MovementResponse> movements;
        try {
            movements = this.movementUseCase.getAllMovements(accountId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new GenericResponse(e.getMessage(), null));
        }
        return ResponseEntity.ok(new GenericResponse("", movements));
    }
}
