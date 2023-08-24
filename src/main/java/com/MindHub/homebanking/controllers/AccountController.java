package com.MindHub.homebanking.controllers;

import com.MindHub.homebanking.dto.AccountDTO;
import com.MindHub.homebanking.dto.ClientDTO;
import com.MindHub.homebanking.models.Account;
import com.MindHub.homebanking.models.Client;
import com.MindHub.homebanking.models.RolType;
import com.MindHub.homebanking.repositories.AccountRepository;
import com.MindHub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/clients/current/accounts")
    public ClientDTO getCurrentClient(Authentication authentication){
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }

    @PostMapping(path = "/accounts")
    public ResponseEntity<Object> register(
            @RequestParam String number, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }
        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password), RolType.CLIENT));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
