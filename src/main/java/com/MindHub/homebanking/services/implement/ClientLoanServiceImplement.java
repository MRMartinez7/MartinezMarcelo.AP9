package com.MindHub.homebanking.services.implement;

import com.MindHub.homebanking.dto.ClientLoanDTO;
import com.MindHub.homebanking.models.ClientLoan;
import com.MindHub.homebanking.repositories.ClientLoanRepository;
import com.MindHub.homebanking.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientLoanServiceImplement implements ClientLoanService {
    @Autowired
    private ClientLoanRepository clientLoanRepository;
    @Override
    public List<ClientLoanDTO> getLoans() {
        return clientLoanRepository.findAll().stream().map(ClientLoanDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClientLoan findById(long id) {
        return clientLoanRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ClientLoan clientLoan) {
        clientLoanRepository.save(clientLoan);
    }
}
