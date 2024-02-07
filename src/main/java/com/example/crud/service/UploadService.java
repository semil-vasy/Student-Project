package com.example.crud.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	String uploadFile(MultipartFile file) throws IOException;

	ResponseEntity<Object> downloadFile(String fileName);
	
}
