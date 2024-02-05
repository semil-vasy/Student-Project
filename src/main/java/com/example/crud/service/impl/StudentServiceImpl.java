package com.example.crud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.dto.ProjectDto;
import com.example.crud.dto.StudentDto;
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

	@Override
	public List<StudentDto> getAllStudent() {
		List<Student> students = studentRepository.findAll();

		List<StudentDto> studentsDto = new ArrayList<StudentDto>();

		for (Student student : students) {
			StudentDto dto = new StudentDto();
			dto.setStudentId(student.getStudentId());
			dto.setStudentName(student.getStudentName());
			dto.setDateOfBirth(student.getDateOfBirth());
			dto.setProject(student.getProject());

			studentsDto.add(dto);
		}
		return studentsDto;
	}

	@Override
	public StudentDto getStudentById(long studentId) throws Exception {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new Exception("No data found with Id : " + studentId));

		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(student.getStudentId());
		studentDto.setStudentName(student.getStudentName());
		studentDto.setDateOfBirth(student.getDateOfBirth());
		studentDto.setProject(student.getProject());

		return studentDto;
	}

	@Override
	public StudentDto addStudent(StudentDto studentDto) {
		Student student = new Student();

		student.setStudentName(studentDto.getStudentName());
		student.setDateOfBirth(studentDto.getDateOfBirth());
		student.setProject(studentDto.getProject());

		Student newStudent = studentRepository.save(student);

		try {
			return getStudentById(newStudent.getStudentId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentDto;
	}

	@Override
	public StudentDto updateStudent(long studentId, StudentDto studentDto) throws Exception {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new Exception("No data found with Id : " + studentId));

		if (studentDto.getStudentName() != null) {
			student.setStudentName(studentDto.getStudentName());
		}
		if (studentDto.getDateOfBirth() != null) {
			student.setDateOfBirth(studentDto.getDateOfBirth());
		}
		if (studentDto.getProject() != null) {
			student.setProject(studentDto.getProject());
		}

		studentRepository.save(student);

		return getStudentById(studentId);

	}

	@Override
	public StudentDto assignProject(long studentId, long projectId) throws Exception {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new Exception("No data found with Id : " + studentId));

		ProjectDto projectDto = projectService.getProjectById(projectId);

		Project project = new Project();
		project.setProjectId(projectDto.getProjectId());
		project.setProjectName(projectDto.getProjectName());

		student.setProject(project);

		studentRepository.save(student);

		return getStudentById(studentId);

	}

	@Override
	public void deleteStudent(long studentId) throws Exception {
		try {
			studentRepository.deleteById(studentId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
