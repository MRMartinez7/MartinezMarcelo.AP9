package com.MindHub.homebanking.Repository;

import com.MindHub.homebanking.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account, Long> {

}