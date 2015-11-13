package edu.spring.restapi.montapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.restapi.montapi.mapping.Client;
import edu.spring.restapi.montapi.repositories.ClientRepository;

@RestController
@RequestMapping("/montapi")
public class ClientsController {

	@Autowired
	private ClientRepository clientRepo;

	@RequestMapping(value = "/client")
	public Client getClient() {
		Client results = clientRepo.findOne(1);

		return results;
	}
}
