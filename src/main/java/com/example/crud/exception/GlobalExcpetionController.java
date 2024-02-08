package com.example.crud.exception;

import java.io.IOException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crud.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExcpetionController {
	
	@ExceptionHandler
	ApiResponse resourceNotFoundException(ResourceNotFoundException exception) {
		return new ApiResponse(404, "Error",exception.getMessage());
	}
	
	@ExceptionHandler
	ApiResponse exceptionHandler(IOException exception) {
		return new ApiResponse(500, "Error",exception.getMessage());
	}
	@ExceptionHandler
	ApiResponse exceptionHandler(Exception exception) {
		return new ApiResponse(404, "Error",exception.getMessage());
	}
}
