package com.example.crud.service;

import java.util.List;

import com.example.crud.dto.StudentDto;

public interface StudentService {

	List<StudentDto> getAllStudent();

	StudentDto getStudentById(long studentId);

	StudentDto addStudent(StudentDto studentDto);

	StudentDto updateStudent(long studentId, StudentDto studentDto);

	void deleteStudent(long studentId);

	StudentDto assignProject(long studentId, long projectId);

}
