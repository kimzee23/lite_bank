package dev.litebank.controller;

import dev.litebank.dto.ErrorResponse;
import dev.litebank.dto.requests.DepositRequest;
import dev.litebank.dto.responses.DepositResponse;
import dev.litebank.exception.AccountNotFoundException;
import dev.litebank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> deposit(@RequestBody DepositRequest depositRequest) {
        try{
            DepositResponse response = accountService.deposit(depositRequest);
            return  ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (AccountNotFoundException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse<>(e.getMessage()));

        }
    }
}
