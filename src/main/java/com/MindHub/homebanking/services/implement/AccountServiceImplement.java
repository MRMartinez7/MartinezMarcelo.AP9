package com.MindHub.homebanking.services.implement;

import com.MindHub.homebanking.dto.AccountDTO;
import com.MindHub.homebanking.models.Account;
import com.MindHub.homebanking.repositories.AccountRepository;
import com.MindHub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplement implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public Boolean existsByNumber(String number) {
        return accountRepository.existsByNumber(number);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
}
