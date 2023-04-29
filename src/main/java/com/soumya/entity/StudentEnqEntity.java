package com.soumya.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table(name="AIT_STUDENT_ENQUIRIES")
@Data
public class StudentEnqEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String studentName;
	private Long studentPhno;
	private String classMode;
	private String courseName;
	
	@CreationTimestamp
	private LocalDate dateCreated;
	private String enqStatus;
	
	@UpdateTimestamp
	private LocalDate lastUpdated;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDtlsEntity user;
}
