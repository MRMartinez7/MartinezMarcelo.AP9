package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dto.LoanDTO;
import com.MindHub.homebanking.models.Loan;

import java.util.List;

public interface LoanService {
    Loan findById(long id);
    boolean existsById(long id);
    List<LoanDTO> getLoans();
}
