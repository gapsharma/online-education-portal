package com.springboot.eduportal.studentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "STUDENT")
public class Student {
	
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", initialValue = 2, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	private Long uuid;
	
	@Column(unique = true)
	private String userId;
	
	private String name;
	
	private String emailAddress;

	public Student() {
	}

	public Student(Long uuid, String userId, String name, String emailAddress) {
		super();
		this.uuid = uuid;
		this.userId = userId;
		this.name = name;
		this.emailAddress = emailAddress;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
}
