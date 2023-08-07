package com.MindHub.homebanking.Repository;

import com.MindHub.homebanking.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long> {

}
