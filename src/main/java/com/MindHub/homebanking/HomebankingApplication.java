package com.MindHub.homebanking;

import com.MindHub.homebanking.Models.Account;
import com.MindHub.homebanking.Models.Client;
import com.MindHub.homebanking.Models.Transaction;
import com.MindHub.homebanking.Models.TransactionType;
import com.MindHub.homebanking.Repository.AccountRepository;
import com.MindHub.homebanking.Repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.MindHub.homebanking.Repository.TransactionRepository;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository ){
		return (args) ->{
			Client client = new Client("Melba","Morel","Melba@mindhub.com");
			clientRepository.save(client);
			Client client2 = new Client("Marcelo","Martinez","Marcelo@mindhub.com");
			clientRepository.save(client2);

			Account account = new Account("VIN001", LocalDate.now(),5000);
			accountRepository.save(account);
			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1),7500);
			accountRepository.save(account1);
			Account account3 = new Account("VIN003", LocalDate.now(),17500);
			accountRepository.save(account3);


			client.addAccount(account1);
			accountRepository.save(account1);

			client.addAccount(account);
			accountRepository.save(account);
			client2.addAccount(account3);
			accountRepository.save(account3);

			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 15000, "Venta",LocalDate.now());
			account.addTransaction(transaction1);
			transactionRepository.save(transaction1);

			Transaction transaction2 = new Transaction(TransactionType.DEBIT, 10000, "Compra",LocalDate.now());
			account.addTransaction(transaction2);
			transactionRepository.save(transaction2);

			Transaction transaction3 = new Transaction(TransactionType.CREDIT, 11000, "Venta",LocalDate.now());
			account1.addTransaction(transaction3);
			transactionRepository.save(transaction3);
			Transaction transaction4 = new Transaction(TransactionType.CREDIT, 21000, "Venta",LocalDate.now());
			account3.addTransaction(transaction4);
			transactionRepository.save(transaction4);


		};

	}
}
