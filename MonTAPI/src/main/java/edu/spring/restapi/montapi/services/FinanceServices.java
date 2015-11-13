package edu.spring.restapi.montapi.services;

/**
 * Interface service to do finance operation on accounts.
 * 
 * @author abdelhakim
 *
 */
public interface FinanceServices {

	/**
	 * Transfer money from one account with the iban source to another one whihc
	 * has the iban target. This function is executed in a transaction. If an
	 * error occurs all the previous statement will be rolled back.
	 * 
	 * @param ibanSource
	 *            Iban for the source account where the account will be removed.
	 * @param ibanTarget
	 *            Iban for the target account where the account will be added.
	 * @param amount
	 *            Amount to be transfered.
	 */
	public void transferMoney(String ibanSource, String ibanTarget, Long amount);

}
