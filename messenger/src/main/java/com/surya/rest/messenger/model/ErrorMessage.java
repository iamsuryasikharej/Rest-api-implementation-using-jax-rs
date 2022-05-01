package com.surya.rest.messenger.model;

public class ErrorMessage {
	private String errorMessage;
	private int errorCode;
	private String Documetation;
	public ErrorMessage()
	{
		
	}
	public ErrorMessage(String errorMessage, int errorCode, String documetation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		Documetation = documetation;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public String getDocumetation() {
		return Documetation;
	}
	
}
