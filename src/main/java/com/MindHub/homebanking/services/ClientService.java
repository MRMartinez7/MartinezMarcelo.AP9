package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dto.ClientDTO;
import com.MindHub.homebanking.models.Client;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getCLients();
    Client findByEmail(String email);
    Client findById(long id);
    void  save(Client client);
}
