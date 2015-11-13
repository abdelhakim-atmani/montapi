package edu.spring.restapi.montapi.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.spring.restapi.montapi.mapping.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {

}
