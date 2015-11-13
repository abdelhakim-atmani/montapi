package edu.spring.restapi.montapi.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.spring.restapi.montapi.mapping.Client;

/**
 * Simple repository to access client table.
 * @author abdelhakim
 *
 */
public interface ClientRepository extends CrudRepository<Client, Integer> {

}
