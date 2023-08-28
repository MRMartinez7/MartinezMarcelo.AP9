package com.MindHub.homebanking.controllers;

import com.MindHub.homebanking.dto.AccountDTO;

import com.MindHub.homebanking.dto.CardDTO;
import com.MindHub.homebanking.models.Account;

import com.MindHub.homebanking.models.Client;
import com.MindHub.homebanking.repositories.AccountRepository;
import com.MindHub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
    @Autowired
    public ClientRepository clientRepository;
    @GetMapping("/accounts/{id}")
        public ResponseEntity<Object> getAccountById(@PathVariable Long id, Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null){
            return new ResponseEntity<>("Account no encontrada", HttpStatus.NOT_FOUND);
        }
        if (account.getOwner().equals(client)){
            AccountDTO accountDTO = new AccountDTO(account);
            return  new ResponseEntity<>(accountDTO, HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>("Esta Account no te pertenece",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/clients/current/accounts")
    public List<AccountDTO> getCurrentAccount(Authentication authentication){
             Client client = clientRepository.findByEmail(authentication.getName());
        return client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toList());
    }
    @PostMapping(path = "/clients/current/accounts")
    public ResponseEntity<Object> registerAccount(Authentication authentication) {

        Client client = clientRepository.findByEmail(authentication.getName());
        if (client == null){
            return new ResponseEntity<>("Client NOT FOUND", HttpStatus.NOT_FOUND);
        }
        if (client.getAccounts().size() < 3) {
            String numberAccounts;
            long randomNum;
            do{
                randomNum =(long) ((Math.random() * (999999 - 1)) + 1);
                numberAccounts = "VIN-"+randomNum;
             }while (accountRepository.existsByNumber(numberAccounts));

            // Crea Aqui
            Account newAccount = new Account(numberAccounts, LocalDate.now(), 0);
            accountRepository.save(newAccount);
            client.addAccount(newAccount);
            clientRepository.save(client);
            return new ResponseEntity<>("Account create", HttpStatus.CREATED);

            }else{
                return new ResponseEntity<>("This client has 3 accounts", HttpStatus.FORBIDDEN);
            }
}
}
