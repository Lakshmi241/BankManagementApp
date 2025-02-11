package com.siprogramming.bankapp.service;

import com.siprogramming.bankapp.entity.Account;
import com.siprogramming.bankapp.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository repo;

    @Override
    public Account createAccount(Account account) {
        return repo.save(account);
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account is not present");
        }
        Account account_found;
        account_found = account.get();
        return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> accountList;
        accountList = repo.findAll();
        return accountList;
    }

    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = account.get();
        Double balance = accountPresent.getAccount_balance();
        Double total = balance + amount;
        accountPresent.setAccount_balance(total);
        repo.save(accountPresent);

        return accountPresent;
    }

    @Override
    public Account withdrawAmount(Long accountNumber, Double amount) {
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = account.get();
        Double balance = accountPresent.getAccount_balance();
        Double total = balance - amount;
        accountPresent.setAccount_balance(total);
        repo.save(accountPresent);

        return accountPresent;
    }

    @Override
    public void closeAccount(Long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber);
        repo.deleteById(accountNumber);
    }
}
