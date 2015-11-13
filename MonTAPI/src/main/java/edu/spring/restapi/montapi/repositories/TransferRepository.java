package edu.spring.restapi.montapi.repositories;

import org.springframework.data.repository.CrudRepository;
import edu.spring.restapi.montapi.mapping.Transfer;

/**
 * Simple repository to access transfer table.
 * @author abdelhakim
 *
 */
public interface TransferRepository extends CrudRepository<Transfer, Integer> {

}
