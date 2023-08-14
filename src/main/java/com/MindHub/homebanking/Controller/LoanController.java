package com.MindHub.homebanking.Controller;

import com.MindHub.homebanking.Models.Loan;
import com.MindHub.homebanking.Repository.LoanRepository;
import com.MindHub.homebanking.dto.LoanDTO;
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
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;
    @GetMapping("/loans")
    public List<LoanDTO> getLoans(){
    List<Loan> allLoans = loanRepository.findAll();
List<LoanDTO> convertedListLoan = allLoans.stream().map(currenteLoan -> new LoanDTO(currenteLoan)).collect(Collectors.toList());
return convertedListLoan;
    }

    @GetMapping("/loans/{id}")
    public LoanDTO getLoansById(@PathVariable long id){
        Optional<Loan> loan = loanRepository.findById(id);
        return new LoanDTO(loan.get());
    }

}
