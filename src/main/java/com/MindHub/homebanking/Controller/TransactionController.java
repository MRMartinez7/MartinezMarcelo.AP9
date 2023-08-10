package com.MindHub.homebanking.Controller;

import com.MindHub.homebanking.Models.Account;
import com.MindHub.homebanking.Models.Transaction;
import com.MindHub.homebanking.Repository.TransactionRepository;
import com.MindHub.homebanking.dto.AccountDTO;
import com.MindHub.homebanking.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(){
        List<Transaction> allTransactions = transactionRepository.findAll();
        List<TransactionDTO> convertedListTransactions = allTransactions.stream().map(currentTransaction -> new TransactionDTO(currentTransaction)).collect(Collectors.toList());
        return convertedListTransactions;
    }
    @GetMapping("/transactions/{id}")
    public TransactionDTO getTransactionById(@PathVariable long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return new TransactionDTO(transaction.get());
    }
}
