package com.MindHub.homebanking;

import com.MindHub.homebanking.models.*;
import com.MindHub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {
@Autowired
private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return (args) ->{
			Client client1 = new Client("Melba","Morel","Melba@mindhub.com",passwordEncoder.encode("1234"),RolType.CLIENT);
			clientRepository.save(client1);
			Client client2 = new Client("Marcelo","Martinez","Marcelo@mindhub.com",passwordEncoder.encode("123456"),RolType.ADMIN);
			clientRepository.save(client2);

			Account account = new Account("VIN001", LocalDate.now(),5000);
			accountRepository.save(account);
			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1),7500);
			accountRepository.save(account1);
			Account account3 = new Account("VIN003", LocalDate.now(),17500);
			accountRepository.save(account3);


			client1.addAccount(account1);
			accountRepository.save(account1);

			client1.addAccount(account);
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

			ClientLoan clientLoan1 = new ClientLoan(400000,60,client1,loan1);
			clientLoanRepository.save(clientLoan1);
			ClientLoan clientLoan2 = new ClientLoan(50000,12,client1,loan2);
			clientLoanRepository.save(clientLoan2);

			ClientLoan clientLoan3 = new ClientLoan(100000,24,client2,loan2);
			clientLoanRepository.save(clientLoan3);
			ClientLoan clientLoan4 = new ClientLoan(200000,36,client2,loan3);
			clientLoanRepository.save(clientLoan4);


			Card card1 = new Card(client1.getFirstName() +" "+ client1.getLastName(),CardType.DEBIT, CardColor.GOLD, "1234-4321-1234-4321",123,LocalDate.now().plusYears(5),LocalDate.now());
			client1.addCard(card1);
			cardRepository.save(card1);
			Card card2 = new Card(client1.getFirstName() +" "+ client1.getLastName(),CardType.CREDIT, CardColor.TITANIUM, "0000-1111-2222-3333",321,LocalDate.now().plusYears(5),LocalDate.now());
			client1.addCard(card2);
			cardRepository.save(card2);

			Card card3 = new Card(client2.getFirstName() +" "+ client2.getLastName(),CardType.CREDIT, CardColor.SILVER, "4444-1111-2222-3333",777,LocalDate.now().plusYears(5),LocalDate.now());
			client2.addCard(card3);
			cardRepository.save(card3);
		};

	}
}
