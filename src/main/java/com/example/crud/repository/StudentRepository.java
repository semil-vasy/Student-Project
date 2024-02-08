package com.example.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.crud.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query(value = "SELECT * FROM student s ORDER BY s.student_id ASC", nativeQuery = true)
	List<Student> findAll();
}
