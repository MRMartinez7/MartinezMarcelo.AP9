package com.MindHub.homebanking.controllers;


import antlr.BaseAST;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/clients")
    public List<ClientDTO> getCLients(){
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable Long id){
        return new ClientDTO(clientRepository.findById(id).orElse(null));
    }
    @RequestMapping("/clients/current")
    public ClientDTO getCurrentClient(Authentication authentication){
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }

@Autowired
public AccountRepository accountRepository;

    @PostMapping(path = "/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);        }
        Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password),RolType.CLIENT);
        clientRepository.save(newClient);

            String numberAccounts;
            long randomNum;
            do{
                randomNum =(long) ((Math.random() * (999999 - 1)) + 1);
                numberAccounts = "VIN-"+randomNum;
            }while (accountRepository.existsByNumber(numberAccounts));
        Account newAccount = new Account(numberAccounts, LocalDate.now(), 0);
        accountRepository.save(newAccount);
        newClient.addAccount(newAccount);
        clientRepository.save(newClient);

        return new ResponseEntity<>(HttpStatus.CREATED);    }
}
