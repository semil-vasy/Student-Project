package com.example.crud.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	String uploadFile(MultipartFile file);

	ResponseEntity<Object> downloadFile(String fileName);
	
}
