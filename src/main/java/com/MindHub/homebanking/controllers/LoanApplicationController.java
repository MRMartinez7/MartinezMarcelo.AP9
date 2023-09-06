package com.MindHub.homebanking.controllers;


import com.MindHub.homebanking.dto.LoanApplicationDTO;
import com.MindHub.homebanking.models.*;
import com.MindHub.homebanking.repositories.AccountRepository;
import com.MindHub.homebanking.repositories.ClientLoanRepository;
import com.MindHub.homebanking.repositories.ClientRepository;
import com.MindHub.homebanking.repositories.LoanRepository;
import com.MindHub.homebanking.services.AccountService;
import com.MindHub.homebanking.services.ClientLoanService;
import com.MindHub.homebanking.services.ClientService;
import com.MindHub.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class LoanApplicationController {
    @Autowired
    private AccountService accountService;
//    private AccountRepository accountRepository;
    @Autowired
    private ClientService clientService;
//    private ClientRepository clientRepository;
    @Autowired
    private LoanService loanService;
//    private LoanRepository loanRepository;
    @Autowired
    private ClientLoanService clientLoanService;
//    private ClientLoanRepository clientLoanRepository;
    @Transactional
    @PostMapping(path = "/loans")
    public ResponseEntity<Object> requestLoans(Authentication authentication,
                                        @RequestBody LoanApplicationDTO loanApplicationDTO){
         Client client = clientService.findByEmail(authentication.getName());
         Loan loan =  loanService.findById(loanApplicationDTO.getLoanId());
         Account accountCredit = accountService.findByNumber(loanApplicationDTO.getToAccountNumber());

         if (loanApplicationDTO.getToAccountNumber().isBlank()) {
            return new ResponseEntity<>("Missing account number data", HttpStatus.FORBIDDEN);
        }
         if (loanApplicationDTO.getPayments() == null) {
            return new ResponseEntity<>("Missing payment data", HttpStatus.FORBIDDEN);
        }
         if (loanApplicationDTO.getAmount() <= 0){
             return new ResponseEntity<>("this amount is invalid",HttpStatus.FORBIDDEN);
         }
         if (loanApplicationDTO.getPayments() == 0){
             return new ResponseEntity<>("this param is null",HttpStatus.FORBIDDEN);
         }
         if (!loanService.existsById(loanApplicationDTO.getLoanId())){
             return new ResponseEntity<>("this loan not exist ",HttpStatus.FORBIDDEN);
         }
//         if (){
//             return new ResponseEntity<>("You already have an active loan ",HttpStatus.FORBIDDEN);
//         }

         if (loanApplicationDTO.getAmount() > loan.getMaxAmount()){
             return new ResponseEntity<>("this amount exceeds the maximum allowed",HttpStatus.FORBIDDEN);
         }
         if (!accountService.existsByNumber(loanApplicationDTO.getToAccountNumber())){
             return new ResponseEntity<>("this amount exceeds the maximum allowed",HttpStatus.FORBIDDEN);
         }
        if(!loan.getPayments().contains(loanApplicationDTO.getPayments())){
             return new ResponseEntity<>("this amount exceeds the maximum allowed",HttpStatus.FORBIDDEN);
         }
         if (!client.getAccounts().contains(accountCredit)){
             return new ResponseEntity<>("this not your account",HttpStatus.FORBIDDEN);
         }

        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount()*(1.2), loanApplicationDTO.getPayments());
        client.addClientLoan(clientLoan);
        loan.addClientLoan(clientLoan);
        clientLoanService.save(clientLoan);
        Transaction creditTransaction = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), "loan approved", LocalDate.now());
        accountCredit.addTransaction(creditTransaction);
        double newBalance = accountCredit.getBalance()+loanApplicationDTO.getAmount();
        accountCredit.setBalance(newBalance);

        return new ResponseEntity<>("Loan Request send", HttpStatus.CREATED);
    }
    }
