package edu.spring.restapi.montapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.spring.restapi.montapi.mapping.Account;

/**
 * Simple repository to access account table.
 * @author abdelhakim
 *
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {

	/**
	 * Get the account which has the iban. 
	 * @param iban Iban to be fetch in the table account.
	 * @return the account which has the iban. 
	 */
	@Query("Select a from Account a WHERE a.iban = :iban")
	public Account getAccountByIban(@Param("iban") String iban);
	
}
