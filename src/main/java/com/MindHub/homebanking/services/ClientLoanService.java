package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dto.ClientLoanDTO;
import com.MindHub.homebanking.models.ClientLoan;

import java.util.List;

public interface ClientLoanService {
    List<ClientLoanDTO> getLoans();
    ClientLoan findById(long id);
    void  save(ClientLoan clientLoan);
}
