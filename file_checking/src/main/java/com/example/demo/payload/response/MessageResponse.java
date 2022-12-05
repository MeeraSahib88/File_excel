package com.example.demo.payload.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {

	
	private int status;
	private String message;
	private Object response;
	private HttpStatus httpStatus;

	public MessageResponse(String string, int status) {
		
		this.message = string;
		this.status = status;
	}

	public MessageResponse(int status, String message, HttpStatus httpStatus) {

		this.status = status;
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
}
