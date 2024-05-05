package com.dbs.account.exception;

public class ErrorHandler {
	private Integer status;
	private String returnMessage;
	
	public ErrorHandler(Integer status, String returnMessage) {
		super();
		this.status = status;
		this.returnMessage = returnMessage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	

}
