package com.MindHub.homebanking.controllers;


import com.MindHub.homebanking.dto.LoanDTO;
import com.MindHub.homebanking.repositories.LoanRepository;
import com.MindHub.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    private LoanService loanService;
//    private LoanRepository loanRepository;


    @GetMapping("/loans")
    public List<LoanDTO> getLoans(){
        return loanService.getLoans();
    }
    @GetMapping("/loans/{id}")
    public LoanDTO getLoanById(@PathVariable Long id){
        return new LoanDTO(loanService.findById(id));
    }

}
