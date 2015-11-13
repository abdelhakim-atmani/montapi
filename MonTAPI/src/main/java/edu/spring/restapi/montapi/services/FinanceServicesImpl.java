package edu.spring.restapi.montapi.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static edu.spring.restapi.montapi.ErrorMessages.*;
import edu.spring.restapi.montapi.FinanceException;
import edu.spring.restapi.montapi.mapping.Account;
import edu.spring.restapi.montapi.mapping.Transfer;
import edu.spring.restapi.montapi.repositories.AccountRepository;
import edu.spring.restapi.montapi.repositories.TransferRepository;

@Component
@Transactional
public class FinanceServicesImpl implements FinanceServices {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private TransferRepository transferRepo;

	@Override
	public boolean transferMoney(String ibanSource, String ibanTarget, Long amount) {

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
			throw new FinanceException(ACCOUNT_NOT_EXISTS + ibanTarget);
		}

		Transfer payment = new Transfer();
		payment.setAccount(accountTarget);
		payment.setType('R');
		payment.setAmount(amount);

		transferRepo.save(payment);

		accountTarget.setAmount(accountTarget.getAmount() + amount);
		accountRepo.save(accountTarget);

		return true;
	}

}
