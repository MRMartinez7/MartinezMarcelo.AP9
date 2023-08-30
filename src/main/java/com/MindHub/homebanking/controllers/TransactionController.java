package com.MindHub.homebanking.controllers;

import com.MindHub.homebanking.dto.TransactionDTO;
import com.MindHub.homebanking.models.*;
import com.MindHub.homebanking.repositories.AccountRepository;
import com.MindHub.homebanking.repositories.ClientRepository;
import com.MindHub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;


    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(){
        return transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }
    @RequestMapping("/transactions/{id}")
    public TransactionDTO getTransactionById(@PathVariable Long id){
        return new TransactionDTO(transactionRepository.findById(id).orElse(null));
    }

}
