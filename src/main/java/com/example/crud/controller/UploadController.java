package com.example.crud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.crud.service.UploadService;

@RestController
@RequestMapping("api/upload")
public class UploadController {

	@Autowired
	UploadService uploadService;

	@PostMapping("")
	ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		return ResponseEntity.ok(uploadService.uploadFile(file));
	}

	@GetMapping("/download/{fileName}")
	ResponseEntity<Object> downloadFile(@PathVariable String fileName) {
		return uploadService.downloadFile(fileName);
	}
}
