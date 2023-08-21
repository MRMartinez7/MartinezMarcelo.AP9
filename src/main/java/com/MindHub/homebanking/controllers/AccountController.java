package com.MindHub.homebanking.controllers;

import com.MindHub.homebanking.dto.AccountDTO;
import com.MindHub.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
        public List<AccountDTO> getAccounts(){
            return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }
    @RequestMapping("/accounts/{id}")
        public AccountDTO getAccountById(@PathVariable Long id){
            return new AccountDTO(accountRepository.findById(id).orElse(null));
    }
}
