package com.MindHub.homebanking.controllers;

import com.MindHub.homebanking.dto.AccountDTO;
import com.MindHub.homebanking.models.Account;
import com.MindHub.homebanking.models.Client;
import com.MindHub.homebanking.repositories.AccountRepository;
import com.MindHub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private ClientRepository clientRepository;
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
}
