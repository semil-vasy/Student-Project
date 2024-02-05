package com.example.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.dto.ApiResponse;
import com.example.crud.dto.ProjectDto;
import com.example.crud.service.ProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping("")
	public ResponseEntity<List<ProjectDto>> getAllProjects() {
		return ResponseEntity.ok(projectService.getAllProject());
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getProjectById(@PathVariable("id") long projectId) {
		try {
			return ResponseEntity.ok(projectService.getProjectById(projectId));
		} catch (Exception e) {
			ApiResponse apiResponse = new ApiResponse(404, e.getMessage());
			return ResponseEntity.status(404).body(apiResponse);
		}
	}

	@PostMapping("")
	public ResponseEntity<ProjectDto> saveProject(@RequestBody ProjectDto projectDto) {
		return ResponseEntity.ok(projectService.addProject(projectDto));
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateProject(@PathVariable("id") long projectId,
			@RequestBody ProjectDto projectDto) {
		try {
			return ResponseEntity.ok(projectService.updateProject(projectId, projectDto));
		} catch (Exception e) {
			ApiResponse apiResponse = new ApiResponse(404, e.getMessage());
			return ResponseEntity.status(404).body(apiResponse);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponse> deleteProject(@PathVariable("id") long projectId) {
		try {
			projectService.deleteProject(projectId);
			ApiResponse apiResponse = new ApiResponse(200, "Deleted");
			return ResponseEntity.status(200).body(apiResponse);
		} catch (Exception e) {
			ApiResponse apiResponse = new ApiResponse(400, e.getMessage());
			return ResponseEntity.status(200).body(apiResponse);
		}
	}
}
