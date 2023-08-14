package com.MindHub.homebanking;

import com.MindHub.homebanking.Models.*;
import com.MindHub.homebanking.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository){
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

			Loan loan1 = new Loan("Hipotecario", 500000, List.of(12,24,36,48,60));
			loanRepository.save(loan1);
			Loan loan2 = new Loan("Personal", 100000,List.of(6,12,24));
			loanRepository.save(loan2);
			Loan loan3 = new Loan("Automotriz",300000,List.of(6,12,24,36));
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan(400000,60,client,loan1);
			clientLoanRepository.save(clientLoan1);
			ClientLoan clientLoan2 = new ClientLoan(50000,12,client,loan2);
			clientLoanRepository.save(clientLoan2);

			ClientLoan clientLoan3 = new ClientLoan(100000,24,client2,loan2);
			clientLoanRepository.save(clientLoan3);
			ClientLoan clientLoan4 = new ClientLoan(200000,36,client2,loan3);
			clientLoanRepository.save(clientLoan4
			);

		};

	}
}
