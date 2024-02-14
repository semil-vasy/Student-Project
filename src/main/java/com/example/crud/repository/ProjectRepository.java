package com.example.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.crud.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
//	@Query(value = "SELECT * FROM project p ORDER BY p.project_id ASC", nativeQuery = true)
//	List<Project> findAll();
}
