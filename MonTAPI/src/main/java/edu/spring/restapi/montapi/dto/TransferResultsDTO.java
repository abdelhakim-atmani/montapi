package edu.spring.restapi.montapi.dto;

/**
 * Results of the transfer.
 * @author abdelhakim
 *
 */
import static edu.spring.restapi.montapi.Messages.FAILED;
import static edu.spring.restapi.montapi.Messages.SUCCESS;

public class TransferResultsDTO {

	/**
	 * status: Status of the transfer sent to the caller. can be only
	 * {@value #FAILED} or {@value #SUCCESS}.
	 */
	private String status;

	/**
	 * errorCode: Error code return by the service. 0 means the transfer was
	 * done successfully.
	 */
	private int errorCode;
	
	/**
	 * message: Description of the operation status.
	 */
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(final int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}
