package edu.spring.restapi.montapi.dto;

public class TransferResultsDTO {

	private String status;
	private int errorCode;
	private String Message;

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
		return Message;
	}

	public void setMessage(final String message) {
		Message = message;
	}

}
