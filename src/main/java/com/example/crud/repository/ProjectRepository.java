package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
