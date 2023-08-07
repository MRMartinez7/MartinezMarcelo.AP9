package com.MindHub.homebanking;

import com.MindHub.homebanking.Models.Account;
import com.MindHub.homebanking.Models.Client;
import com.MindHub.homebanking.Repository.AccountRepository;
import com.MindHub.homebanking.Repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository ){
		return (args) ->{
			Client client = new Client("Melba","Morel","Melba@mindhub.com");
			clientRepository.save(client);
			//Client client2 = new Client("Melba","Morel","Melba@mindhub.com");
			//clientRepository.save(client2);
			Account account = new Account("VIN001", LocalDate.now(),5000);
			accountRepository.save(account);
			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1),7500);
			accountRepository.save(account1);
			/*Account account2 = new Account("VIN001", LocalDate.now(),5000,client);
			accountRepository.save(account2);
			Account account3 = new Account("VIN001", LocalDate.now(),5000,client2);
			accountRepository.save(account3);
			Account account4 = new Account("VIN001", LocalDate.now(),5000,client2);
			accountRepository.save(account4);*/
		};

	}
}
