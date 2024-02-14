package com.example.crud.dto;

import com.example.crud.model.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProjectDto {

	private long projectId;

	private String projectName;

	private Student student;
}
