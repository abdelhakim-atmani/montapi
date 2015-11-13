package edu.spring.restapi.montapi.services;

public interface FinanceServices {

	public boolean transferMoney(String ibanSource, String ibanTarget, Long amount);

}
