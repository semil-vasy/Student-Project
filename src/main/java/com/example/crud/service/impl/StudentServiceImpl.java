package com.example.crud.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.dto.ProjectDto;
import com.example.crud.dto.StudentDto;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.Project;
import com.example.crud.model.Student;
import com.example.crud.repository.StudentRepository;
import com.example.crud.service.ProjectService;
import com.example.crud.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<StudentDto> getAllStudent() {
		List<Student> students = studentRepository.findAll();
		return students.stream().map(this::studentToDto).toList();
	}

	@Override
	public StudentDto getStudentById(long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("No data found with Id : " + studentId));
		return this.studentToDto(student);
	}

	@Override
	public StudentDto addStudent(StudentDto studentDto) {
		Student student = studentRepository.save(this.dtoToStudent(studentDto));
		return this.studentToDto(student);

	}

	@Override
	public StudentDto updateStudent(long studentId, StudentDto studentDto) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("No data found with Id : " + studentId));

		student.setStudentName(studentDto.getStudentName());
		student.setDateOfBirth(studentDto.getDateOfBirth());
		student.setProject(studentDto.getProject());

		Student newStudent = studentRepository.save(student);
		return this.studentToDto(newStudent);

	}

	@Override
	public StudentDto assignProject(long studentId, long projectId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("No data found with Id : " + studentId));

		ProjectDto projectDto = projectService.getProjectById(projectId);

		Project project = new Project();
		project.setProjectId(projectDto.getProjectId());
		project.setProjectName(projectDto.getProjectName());

		student.setProject(project);

		Student newStudent = studentRepository.save(student);
		return this.studentToDto(newStudent);

	}

	@Override
	public void deleteStudent(long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("No data found with Id : " + studentId));
		studentRepository.delete(student);
	}

	Student dtoToStudent(StudentDto studentDto) {
		return this.modelMapper.map(studentDto, Student.class);
	}

	StudentDto studentToDto(Student student) {
		return this.modelMapper.map(student, StudentDto.class);
	}

}
