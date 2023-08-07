package com.MindHub.homebanking.Controller;

import com.MindHub.homebanking.Models.Client;
import com.MindHub.homebanking.Repository.ClientRepository;
import com.MindHub.homebanking.dto.ClientDTO;
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
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        List<Client> allClients = clientRepository.findAll();
        List<ClientDTO> convertedListClients = allClients.stream().map(currentClient -> new ClientDTO(currentClient)).collect(Collectors.toList());
        return convertedListClients;
    }
    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable long id){
        Optional<Client> client = clientRepository.findById(id);
        return new ClientDTO(client.get());
    }
}
