package edu.spring.restapi.montapi.controllers;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.restapi.montapi.mapping.Client;
import edu.spring.restapi.montapi.repositories.ClientRepository;

/**
 * All services which are related to the client object.
 * @author abdelhakim
 *
 */
// Define the bean as a RestController.
@RestController
// All services path defined in this controller will start with /montapi.
@RequestMapping("/montapi")
public class ClientsController {

	/**
	 * log: To put log in the defined outpout stream.
	 */
	private Logger log = Logger.getLogger(ClientsController.class);
	
	/**
	 * clientRepo: To access to the data contained in the table clients.
	 */
	@Autowired
	private ClientRepository clientRepo;

	/**
	 * Return all clients contained in the DB.
	 * @return all clients contained in the DB.
	 */
	@RequestMapping(value = "/clients")
	public Iterable<Client> getClients() {
		log.debug("Starting loading all clients in the service /clients.");
		Iterable<Client> results = clientRepo.findAll();
		log.debug("End loading all clients in the service /clients.");
		return results;
	}
}
