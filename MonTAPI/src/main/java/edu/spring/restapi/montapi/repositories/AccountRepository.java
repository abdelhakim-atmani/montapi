package edu.spring.restapi.montapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.spring.restapi.montapi.mapping.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	@Query("Select a from Account a WHERE a.iban = :iban")
	Account getAccountByIban(@Param("iban") String iban);
	
}
