package edu.spring.restapi.montapi;

/**
 * Final class to store all error messages.
 * @todo Replace this class by a ressource file.
 * @author abdelhakim
 *
 */
public final class Messages {

	/**
	 * ACCOUNT_NOT_EXISTS: When the query by iban in the DB is empty.
	 */
	public static String ACCOUNT_NOT_EXISTS = "No account found with the iban : ";
	
	/**
	 * NO_ENOUGH_MONEY: The source account has no enougn money.
	 */
	public static String NO_ENOUGH_MONEY = "The source account has no enough money for this operation. Current account balance : ";
	
	/**
	 * SUCCESS: When the operation finished in successfully.
	 */
	public static String SUCCESS = "success";
	
	/**
	 * FAILED: When the operation failed.
	 */
	public static String FAILED = "failed";
	
}
