package com.soumya.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="AIT_ENQUIRY_STATUS")
@Data
public class EnqStatusEntity {
	@Id
	@GeneratedValue
	private Integer statusId;
	private String statusName;

}
