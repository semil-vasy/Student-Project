package com.example.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import static jakarta.persistence.CascadeType.*;

@Entity
@Data
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	
	private String projectName;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id", referencedColumnName = "studentId")
	private Student student;

}
