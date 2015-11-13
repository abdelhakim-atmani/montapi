package edu.spring.restapi.montapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import edu.spring.restapi.montapi.mapping.Account;
import edu.spring.restapi.montapi.mapping.Client;
import edu.spring.restapi.montapi.repositories.AccountRepository;
import edu.spring.restapi.montapi.repositories.ClientRepository;

@Component
public class DBLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private ClientRepository clientRepository;
	
	@Autowired
    private AccountRepository accountRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	System.out.println("------------- Creating new data in the DB ----------------");
		Client client = new Client();
		client.setFirstName("Test");
		client.setLastName("Toto");
		client.setIdCard(1234568);
		
		client = clientRepository.save(client);
		
		
		Account account = new Account();
		account.setActive(true);
		account.setClient(client);
		account.setAmount(200L);
		account.setIban("FR38391893839");
		
		accountRepository.save(account);
		
		Account account2 = new Account();
		account2.setActive(true);
		account2.setAmount(300L);
		account2.setClient(client);
		account2.setIban("FR429284595");

		accountRepository.save(account2);
		
    }
}
