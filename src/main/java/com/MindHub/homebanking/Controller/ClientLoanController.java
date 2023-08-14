package com.MindHub.homebanking.Controller;

import com.MindHub.homebanking.Models.ClientLoan;
import com.MindHub.homebanking.Models.Loan;
import com.MindHub.homebanking.Repository.ClientLoanRepository;
import com.MindHub.homebanking.dto.ClientLoanDTO;
import com.MindHub.homebanking.dto.LoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientLoanController {
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @GetMapping("/clientLoans")
    public Set<ClientLoanDTO> getLoans(){
        List<ClientLoan> allClientLoans = clientLoanRepository.findAll();
        Set<ClientLoanDTO> convertedListClientLoan = allClientLoans.stream().map(currenteClientLoan -> new ClientLoanDTO(currenteClientLoan)).collect(Collectors.toSet());
        return convertedListClientLoan;
    }

    @GetMapping("/clientLoans/{id}")
    public ClientLoanDTO getClientLoanById(@PathVariable long id){
        Optional<ClientLoan> clientLoans = clientLoanRepository.findById(id);
        return new ClientLoanDTO(clientLoans.get());
    }
}
