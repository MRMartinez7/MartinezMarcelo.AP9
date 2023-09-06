package com.MindHub.homebanking.services.implement;

import com.MindHub.homebanking.dto.ClientDTO;
import com.MindHub.homebanking.models.Client;
import com.MindHub.homebanking.repositories.ClientRepository;
import com.MindHub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService{
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public List<ClientDTO> getCLients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Client findById(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Client client) {
    clientRepository.save(client);
    }
}
