package com.example.crud.service;

import java.util.List;

import com.example.crud.dto.ProjectDto;

public interface ProjectService {

	List<ProjectDto> getAllProject();

	ProjectDto getProjectById(long projectId);

	ProjectDto addProject(long studentId, ProjectDto projectDto);

	ProjectDto updateProject(long projectId, ProjectDto projectDto);

	void deleteProject(long projectId);

}
