package com.example.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.dto.ApiResponse;
import com.example.crud.dto.StudentDto;
import com.example.crud.service.StudentService;

@RestController
@RequestMapping("api/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("")
	public ResponseEntity<List<StudentDto>> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudent());
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") long studentId) {
		try {
			return ResponseEntity.ok(studentService.getStudentById(studentId));
		} catch (Exception e) {
			ApiResponse apiResponse = new ApiResponse(404, e.getMessage());
			return ResponseEntity.ok(apiResponse);
		}
	}

	@PostMapping("")
	public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
		return ResponseEntity.ok(studentService.addStudent(studentDto));
	}

	@PutMapping("{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable long studentId, @RequestBody StudentDto studentDto) {
		try {
			return ResponseEntity.ok(studentService.updateStudent(studentId, studentDto));
		} catch (Exception e) {
			ApiResponse apiResponse = new ApiResponse(404, e.getMessage());
			return ResponseEntity.ok(apiResponse);
		}
	}

	@GetMapping("{studentId}/{projectId}")
	public ResponseEntity<?> getStudentById(@PathVariable long studentId, @PathVariable long projectId) {
		try {
			return ResponseEntity.ok(studentService.assignProject(studentId, projectId));
		} catch (Exception e) {
			ApiResponse apiResponse = new ApiResponse(404, e.getMessage());
			return ResponseEntity.ok(apiResponse);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("id") long studentId) {
		try {
			studentService.deleteStudent(studentId);
			ApiResponse apiResponse = new ApiResponse(200, "Deleted");
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse apiResponse = new ApiResponse(404, e.getMessage());
			return ResponseEntity.ok(apiResponse);
		}
	}

}
