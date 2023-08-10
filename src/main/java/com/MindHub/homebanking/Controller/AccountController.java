package com.MindHub.homebanking.Controller;

import com.MindHub.homebanking.Models.Account;
import com.MindHub.homebanking.Models.Client;
import com.MindHub.homebanking.Repository.AccountRepository;
import com.MindHub.homebanking.dto.AccountDTO;
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
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        List<Account> allAccounts = accountRepository.findAll();
        List<AccountDTO> convertedListAccounts = allAccounts.stream().map(currentAccount -> new AccountDTO(currentAccount)).collect(Collectors.toList());
        return convertedListAccounts;
    }
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccountById(@PathVariable long id){
        Optional<Account> account = accountRepository.findById(id);
        return new AccountDTO(account.get());
    }
}
