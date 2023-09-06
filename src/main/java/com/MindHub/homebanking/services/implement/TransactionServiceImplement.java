package com.MindHub.homebanking.services.implement;

import com.MindHub.homebanking.dto.TransactionDTO;
import com.MindHub.homebanking.models.Transaction;
import com.MindHub.homebanking.repositories.TransactionRepository;
import com.MindHub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class TransactionServiceImplement implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public Transaction findById(long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        return transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
