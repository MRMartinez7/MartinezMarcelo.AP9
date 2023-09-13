package com.MindHub.homebanking.controllers;

import com.MindHub.homebanking.dto.TransactionDTO;
import com.MindHub.homebanking.models.*;
import com.MindHub.homebanking.repositories.AccountRepository;
import com.MindHub.homebanking.repositories.ClientRepository;
import com.MindHub.homebanking.repositories.TransactionRepository;
import com.MindHub.homebanking.services.AccountService;
import com.MindHub.homebanking.services.ClientService;
import com.MindHub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {
//    @Autowired
//    private TransactionRepository transactionRepository;
@Autowired
private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(){
        return transactionService.getTransactions();
    }
    @GetMapping("/transactions/{id}")
    public TransactionDTO getTransactionById(@PathVariable Long id){
        return new TransactionDTO(transactionService.findById(id));
    }
//    @Autowired
//    public ClientRepository clientRepository;
//    @Autowired
//    public AccountRepository accountRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Transactional
    @PostMapping(path = "/transactions")
    public ResponseEntity<Object> transfer(Authentication authentication,
                                           @RequestParam String fromAccountNumber,
                                           @RequestParam String toAccountNumber,
                                           @RequestParam double amount,
                                           @RequestParam String description) {
        Client client = clientService.findByEmail(authentication.getName());
        Account accountDebit = accountService.findByNumber(fromAccountNumber);
        Account accountCredit = accountService.findByNumber(toAccountNumber);

        if (fromAccountNumber.isBlank() && toAccountNumber.isBlank() && amount > 1 && description.isBlank()) {
            return new ResponseEntity<>("Missing data.", HttpStatus.FORBIDDEN);
        }
        if (fromAccountNumber.equals(toAccountNumber)){
            return new ResponseEntity<>("Transaction invalid, equals accounts.", HttpStatus.FORBIDDEN);
        }
        if (!accountService.existsByNumber(fromAccountNumber)){
            return new ResponseEntity<>("this account not exist", HttpStatus.FORBIDDEN);
        }
        if (client.getAccounts().equals(fromAccountNumber)){
            return  new ResponseEntity<>("unauthenticated client", HttpStatus.FORBIDDEN);
        }
        if (!accountService.existsByNumber(toAccountNumber)){
            return  new ResponseEntity<>("non-existent destination accounts", HttpStatus.FORBIDDEN);
        }
        if (accountDebit.getBalance() < amount){
            return  new ResponseEntity<>("this account don't have enough amount" ,HttpStatus.FORBIDDEN);
        }
        if (amount < 0){
            return  new ResponseEntity<>("negative balances are not allowed", HttpStatus.FORBIDDEN);
        }
        double originTransaction = accountDebit.getBalance()-amount;
        double destinyTransaction = accountCredit.getBalance()+amount;
        accountDebit.setBalance(originTransaction);
        accountCredit.setBalance(destinyTransaction);
        Transaction debitTransaction = new Transaction(TransactionType.DEBIT, -amount, description,LocalDate.now());
        accountDebit.addTransaction(debitTransaction);
        Transaction creditTransaction = new Transaction(TransactionType.CREDIT, amount, description,LocalDate.now());
        accountCredit.addTransaction(creditTransaction);
        transactionService.save(debitTransaction);
        transactionService.save(creditTransaction);

        return new ResponseEntity<>("successful transaction", HttpStatus.CREATED);
    }
}








