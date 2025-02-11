package com.siprogramming.bankapp.controller;

import com.siprogramming.bankapp.entity.Account;
import com.siprogramming.bankapp.service.AccountService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService service;

    //create account
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createAccount = service.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }

    @GetMapping("/{id}")
    public Account getAccountDetailsByAccountNumber(@PathVariable("id") Long accountNumber) {
        Account account = service.getAccountDetailsByAccountNumber(accountNumber);
        return account;
    }

    @GetMapping("/getallaccounts")
    public List<Account> getAllAccountDetails() {
        List<Account> accounts = service.getAllAccountDetails();
        return accounts;
    }

    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account depositAccount(@PathVariable Long accountNumber, @PathVariable Double amount) {
        Account account = service.depositAmount(accountNumber, amount);
        return account;
    }

    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public Account withdrawAccount(@PathVariable Long accountNumber, @PathVariable Double amount) {
        Account account = service.withdrawAmount(accountNumber, amount);
        return account;
    }

    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber) {
        service.closeAccount(accountNumber);
        return ResponseEntity.ok("Account closed");
    }
}
