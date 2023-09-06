package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dto.AccountDTO;
import com.MindHub.homebanking.models.Account;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAccounts();
    Account findById(long id);
    Account findByNumber(String number);
    Boolean existsByNumber(String number);
     void  save(Account account);

}
