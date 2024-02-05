package com.example.crud.service;

import java.util.List;

import com.example.crud.dto.StudentDto;

public interface StudentService {

	List<StudentDto> getAllStudent();

	StudentDto getStudentById(long studentId) throws Exception;

	StudentDto addStudent(StudentDto student);

	StudentDto updateStudent(long studentId, StudentDto student) throws Exception;

	StudentDto assignProject(long studentId, long projectId) throws Exception;

	void deleteStudent(long studentId) throws Exception;

}
