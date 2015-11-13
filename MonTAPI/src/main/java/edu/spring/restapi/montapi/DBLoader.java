package edu.spring.restapi.montapi;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import edu.spring.restapi.montapi.mapping.Account;
import edu.spring.restapi.montapi.mapping.Client;
import edu.spring.restapi.montapi.repositories.AccountRepository;
import edu.spring.restapi.montapi.repositories.ClientRepository;

/**
 * At the startup of the server, some data are stored in the database to be able
 * to do some test with the jsondoc api.
 * 
 * @author abdelhakim
 *
 */
@Component
public class DBLoader implements ApplicationListener<ContextRefreshedEvent> {

	/**
	 * log: To put log in the defined outpout stream.
	 */
	private Logger log = Logger.getLogger(DBLoader.class);
	
	/**
	 * clientRepository: Repository to access clients data.
	 */
	@Autowired
	private ClientRepository clientRepository;

	/**
	 * accountRepository: Repository to access accounts data.
	 */
	@Autowired
	private AccountRepository accountRepository;

	/**
	 * Called when the application is starting or refreshed.
	 * @param event:
	 *            Event raised when an application gets initialized or
	 *            refreshed.
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("------------- Initialization of the data in the DB ----------------");
		
		Client client = new Client();
		client.setFirstName("Test");
		client.setLastName("Toto");
		client.setIdCard(1234568);
		
		// Insert a client in the database.
		client = clientRepository.save(client);

		Account account = new Account();
		account.setActive(true);
		account.setClient(client);
		account.setAmount(200L);
		account.setIban("FR38391893839");

		// Insert an account in the database which is linked to the client.
		accountRepository.save(account);

		Account account2 = new Account();
		account2.setActive(true);
		account2.setAmount(300L);
		account2.setClient(client);
		account2.setIban("FR429284595");

		// Insert an account in the database which is linked to the client.
		accountRepository.save(account2);
		
		log.info("------------- End of the initialization of the data in the DB ----------------");

	}
}
