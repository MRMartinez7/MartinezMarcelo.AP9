package com.MindHub.homebanking.repositories;

import com.MindHub.homebanking.models.Account;
import com.MindHub.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepository  extends JpaRepository<Account, Long> {
    Account findByNumber(String number);
}