package com.MindHub.homebanking.controllers;


import com.MindHub.homebanking.dto.ClientLoanDTO;
import com.MindHub.homebanking.repositories.ClientLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientLoanController {
    @Autowired
    private ClientLoanRepository clientLoanRepository;


    @GetMapping("/clientsLoans")
    public List<ClientLoanDTO> getLoans(){
        return clientLoanRepository.findAll().stream().map(ClientLoanDTO::new).collect(Collectors.toList());
    }
    @RequestMapping("/clientsLoans/{id}")
    public ClientLoanDTO getClientLoanById(@PathVariable Long id){
        return new ClientLoanDTO(clientLoanRepository.findById(id).orElse(null));
    }

}
