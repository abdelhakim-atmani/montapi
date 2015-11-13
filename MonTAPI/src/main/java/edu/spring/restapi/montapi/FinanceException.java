package edu.spring.restapi.montapi;

import org.springframework.dao.DataAccessException;

/**
 * For the business the DataAccessException needs to be extended to have a more
 * understable and explicit code. When this exception is thrown it means that
 * it has been returned by the application.
 * 
 * @author abdelhakim
 *
 */
public class FinanceException extends DataAccessException {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -1234578016961739603L;

	/**
	 * Constructor for FinanceException.
	 * 
	 * @param msg
	 *            the detail message
	 */
	public FinanceException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for FinanceException.
	 * 
	 * @param msg
	 *            the detail message
	 * @param cause
	 *            the root cause (usually from using a underlying data access
	 *            API such as JDBC)
	 */
	public FinanceException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
