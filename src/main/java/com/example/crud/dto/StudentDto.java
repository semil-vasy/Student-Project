package com.example.crud.dto;

import java.util.Date;
import com.example.crud.model.Project;
import lombok.Data;

@Data
public class StudentDto {

	private long studentId;

	private String studentName;

	private Date dateOfBirth;

	private Project project;
}
