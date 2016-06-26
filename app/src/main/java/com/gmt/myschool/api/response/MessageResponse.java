package com.gmt.myschool.api.response;

import com.google.gson.annotations.Expose;

public class MessageResponse extends SuperResponse{

	@Expose
	private String message;
	
	public MessageResponse() {
		super();
		this.message = "";
	}
	
	public MessageResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
