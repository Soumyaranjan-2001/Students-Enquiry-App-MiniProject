package com.soumya.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="AIT_COURSES")
@Data

public class CourseEntity {
	
	@Id
	@GeneratedValue
	private Integer CourseId;
	private String CourseName;
	

}
