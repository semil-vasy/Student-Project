package com.example.crud.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crud.dto.ProjectDto;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.Project;
import com.example.crud.repository.ProjectRepository;
import com.example.crud.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProjectDto> getAllProject() {
		List<Project> projects = projectRepository.findAll();
		return projects.stream().map(this::projectToDto).toList();
	}

	@Override
	public ProjectDto getProjectById(long projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("No data found with Id : " + projectId));
		return this.projectToDto(project);
	}

	@Override
	public ProjectDto addProject(ProjectDto projectDto) {
		Project project = projectRepository.save(this.dtoToProject(projectDto));
		return this.projectToDto(project);
	}

	@Override
	public ProjectDto updateProject(long projectId, ProjectDto projectDto) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("No data found with Id : " + projectId));

		project.setProjectName(projectDto.getProjectName());

		Project newProject = projectRepository.save(project);
		return this.projectToDto(newProject);

	}

	@Override
	public void deleteProject(long projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("No data found with Id : " + projectId));
		projectRepository.delete(project);
	}

	public Project dtoToProject(ProjectDto projectDto) {
		return this.modelMapper.map(projectDto, Project.class);
	}

	public ProjectDto projectToDto(Project project) {
		return this.modelMapper.map(project, ProjectDto.class);
	}

}
