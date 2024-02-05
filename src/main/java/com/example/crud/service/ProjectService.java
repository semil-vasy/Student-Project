package com.example.crud.service;

import java.util.List;

import com.example.crud.dto.ProjectDto;

public interface ProjectService {

	List<ProjectDto> getAllProject();

	ProjectDto getProjectById(long projectId) throws Exception;

	ProjectDto addProject(ProjectDto project);

	ProjectDto updateProject(long projectId, ProjectDto project) throws Exception;

	void deleteProject(long projectId) throws Exception;

}
