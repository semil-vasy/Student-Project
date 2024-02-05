package com.example.crud.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crud.dto.ProjectDto;
import com.example.crud.model.Project;
import com.example.crud.repository.ProjectRepository;
import com.example.crud.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public List<ProjectDto> getAllProject() {
		List<Project> projects = projectRepository.findAll();

		List<ProjectDto> projectsDto = new ArrayList<ProjectDto>();

		for (Project project : projects) {
			ProjectDto dto = new ProjectDto();
			dto.setProjectId(project.getProjectId());
			dto.setProjectName(project.getProjectName());
			projectsDto.add(dto);
		}
		return projectsDto;
	}

	@Override
	public ProjectDto getProjectById(long projectId) throws Exception {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new Exception("No data found with Id : " + projectId));

		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(project.getProjectId());
		projectDto.setProjectName(project.getProjectName());

		return projectDto;
	}

	@Override
	public ProjectDto addProject(ProjectDto projectDto) {
		Project project = new Project();
		project.setProjectName(projectDto.getProjectName());

		Project newProject = projectRepository.save(project);

		try {
			return getProjectById(newProject.getProjectId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projectDto;
	}

	@Override
	public ProjectDto updateProject(long projectId, ProjectDto projectDto) throws Exception {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new Exception("No data found with Id : " + projectId));
		if (projectDto.getProjectName() != null) {
			project.setProjectName(projectDto.getProjectName());
		}
		projectRepository.save(project);
		try {
			return getProjectById(projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projectDto;
	}

	@Override
	public void deleteProject(long projectId) throws Exception {
		try {
			projectRepository.deleteById(projectId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
