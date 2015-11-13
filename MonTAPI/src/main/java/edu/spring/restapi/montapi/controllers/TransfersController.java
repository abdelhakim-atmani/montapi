package edu.spring.restapi.montapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	 * financeService: Service to do operation on the accounts.
	 */
	@Autowired
	private FinanceServices financeService;

	/**
	 * Service to transfer money from one account to another one
	 * @param ibanSource IBAN of the account where the money will be removed.
	 * @param ibanTarget IBAN of the account where the money will be added
	 * @param amount The amount of this transfer.
	 * @return The result of the transfer with the different error code:
	 * 			- 0: Operation done successfully
	 * 			- 1: An unknown error occurs during the transfer
	 * 			- 2: An error occurs during the transfer and the detail of the error is in the error message.
	 */
	@RequestMapping(value = "/transfer/{iban_source}/{iban_target}/{amount}")
	public TransferResultsDTO transferMoney(@PathVariable(value = "iban_source") String ibanSource,
			@PathVariable(value = "iban_target") String ibanTarget, @PathVariable(value = "amount") Long amount) {
		TransferResultsDTO results = new TransferResultsDTO();
		try {
			boolean operationStatus = financeService.transferMoney(ibanSource, ibanTarget, amount);
			if (operationStatus) {
				results.setErrorCode(0);
				results.setMessage("Operation done successfully.");
				results.setStatus("success");
			} else {
				results.setErrorCode(1);
				results.setMessage("Operation failed with unknown reason.");
				results.setStatus("failed");
			}
		} catch (FinanceException ex) {
			results.setErrorCode(2);
			results.setMessage("Operation failed with the following error: " + ex.getMessage());
			results.setStatus("failed");
		}
		return results;
	}

}
