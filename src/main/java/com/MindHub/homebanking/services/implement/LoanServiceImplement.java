package com.MindHub.homebanking.services.implement;

import com.MindHub.homebanking.dto.LoanDTO;
import com.MindHub.homebanking.models.Loan;
import com.MindHub.homebanking.repositories.LoanRepository;
import com.MindHub.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplement implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan findById(long id) {
        return loanRepository.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return loanRepository.existsById(id);
    }

    @Override
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toList());
    }
}
