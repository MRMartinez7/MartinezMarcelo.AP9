package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dto.TransactionDTO;
import com.MindHub.homebanking.models.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction findById(long id);
    List<TransactionDTO> getTransactions();
    void save(Transaction transaction);
}
