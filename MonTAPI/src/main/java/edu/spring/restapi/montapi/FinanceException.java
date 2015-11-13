package edu.spring.restapi.montapi;

import org.springframework.dao.DataAccessException;

public class FinanceException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1234578016961739603L;

	/**
	 * Constructor for DataAccessException.
	 * @param msg the detail message
	 */
	public FinanceException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for DataAccessException.
	 * @param msg the detail message
	 * @param cause the root cause (usually from using a underlying
	 * data access API such as JDBC)
	 */
	public FinanceException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
