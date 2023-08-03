package com.MindHub.homebanking;

import com.MindHub.homebanking.Models.Client;
import com.MindHub.homebanking.Repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner iniData(ClientRepository clientRepository){
		return (args) ->{
			Client client= new Client("35264423", "Marcelo", "Martinez");
			clientRepository.save(client);
		};
	}*/
}
