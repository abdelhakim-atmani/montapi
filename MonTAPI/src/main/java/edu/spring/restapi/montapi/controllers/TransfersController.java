package edu.spring.restapi.montapi.controllers;

import static edu.spring.restapi.montapi.Messages.FAILED;
import static edu.spring.restapi.montapi.Messages.SUCCESS;

import org.jboss.logging.Logger;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.restapi.montapi.FinanceException;
import edu.spring.restapi.montapi.dto.TransferResultsDTO;
import edu.spring.restapi.montapi.services.FinanceServices;

/**
 * 
 * @author abdelhakim
 *
 */
@RestController
@RequestMapping("/montapi")
public class TransfersController {

	/**
	 * log: To put log in the defined outpout stream.
	 */
	private Logger log = Logger.getLogger(ClientsController.class);

	/**
	 * financeService: Service to do operation on the accounts.
	 */
	@Autowired
	private FinanceServices financeService;

	/**
	 * Service to transfer money from one account to another one
	 * 
	 * @param ibanSource
	 *            IBAN of the account where the money will be removed.
	 * @param ibanTarget
	 *            IBAN of the account where the money will be added.
	 * @param amount
	 *            The amount of this transfer.
	 * @return The result of the transfer with the different error code: <br>
	 *         - 0: Operation done successfully <br>
	 *         - 1: An unknown error occurs during the transfer <br>
	 *         - 2: An error occurs during the transfer and the detail of the
	 *         error is in the error message.
	 */
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)

	public TransferResultsDTO transferMoneyByPost(
			@ApiQueryParam(description = "IBAN of the account where the money will be removed.") @RequestParam(value = "iban_source") String ibanSource,
			@ApiQueryParam(description = "IBAN of the account where the money will be added.") @RequestParam(value = "iban_target") String ibanTarget,
			@ApiQueryParam(description = "The amount of this transfer.") @RequestParam(value = "amount") Long amount) {
		log.debug("Starting the transfer from the account " + ibanSource + " to " + ibanTarget + " with the amont "
				+ amount);
		TransferResultsDTO results = new TransferResultsDTO();
		try {
			// Run the transfer through the finance service to have it in a
			// transaction.
			financeService.transferMoney(ibanSource, ibanTarget, amount);
			results.setErrorCode(0);
			results.setMessage("Operation done successfully.");
			results.setStatus(SUCCESS);
		} catch (FinanceException ex) {
			results.setErrorCode(1);
			results.setMessage(ex.getMessage());
			results.setStatus(FAILED);
			log.error("Transfer failed with the following error => " + ex.getMessage());
		}
		log.debug("End of the transfer from the account " + ibanSource + " to " + ibanTarget + " with the amont "
				+ amount);
		return results;
	}

}
