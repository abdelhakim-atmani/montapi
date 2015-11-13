package edu.spring.restapi.montapi.services;

import static edu.spring.restapi.montapi.Messages.ACCOUNT_NOT_EXISTS;
import static edu.spring.restapi.montapi.Messages.NO_ENOUGH_MONEY;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.restapi.montapi.FinanceException;
import edu.spring.restapi.montapi.mapping.Account;
import edu.spring.restapi.montapi.mapping.Transfer;
import edu.spring.restapi.montapi.repositories.AccountRepository;
import edu.spring.restapi.montapi.repositories.TransferRepository;

/**
 * Implementation of the interface FinanceServices. This service will execute
 * queries in a transaction.
 * 
 * @author abdelhakim
 *
 */
@Service
@Transactional
public class FinanceServicesImpl implements FinanceServices {

	/**
	 * accountRepo: To access to account table.
	 */
	@Autowired
	private AccountRepository accountRepo;

	/**
	 * transferRepo: To access to transfer table.
	 */
	@Autowired
	private TransferRepository transferRepo;

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
	 * @return status of the transfer: <br>
	 *         - true if the operation has been done successfully - false if an
	 *         unknown error has been encoured.
	 */
	@Override
	public void transferMoney(String ibanSource, String ibanTarget, Long amount) {

		Account accountSource = accountRepo.getAccountByIban(ibanSource);
		Account accountTarget = accountRepo.getAccountByIban(ibanTarget);

		if (accountSource == null) {
			throw new FinanceException(ACCOUNT_NOT_EXISTS + ibanSource);
		}

		if (accountSource.getAmount() > amount) {
			Transfer payment = new Transfer();
			payment.setAccount(accountSource);
			payment.setType('P');
			payment.setAmount(amount);

			transferRepo.save(payment);

			accountSource.setAmount(accountSource.getAmount() - amount);
			accountRepo.save(accountSource);
		} else {
			throw new FinanceException(NO_ENOUGH_MONEY);
		}

		if (accountTarget == null) {
			// because the service is running in a transaction this exeption will rollback the previous transfer inert.
			throw new FinanceException(ACCOUNT_NOT_EXISTS + ibanTarget);
		}

		Transfer payment = new Transfer();
		payment.setAccount(accountTarget);
		payment.setType('R');
		payment.setAmount(amount);

		transferRepo.save(payment);

		accountTarget.setAmount(accountTarget.getAmount() + amount);
		accountRepo.save(accountTarget);

	}

}
