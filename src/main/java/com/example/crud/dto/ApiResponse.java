package com.example.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

	private int status;
	
	private String message;
	
	private Object data;

}
