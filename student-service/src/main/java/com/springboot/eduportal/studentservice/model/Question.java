package com.springboot.eduportal.studentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Question {

	@Id
	@SequenceGenerator(name = "question_sequence", sequenceName = "question_sequence", initialValue = 2, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_sequence")
	private Long id;
	
	private Long studentId;
	
	private Long courseId;
	
	private String status;
	
	private String text;

	public Question() {
		super();
	}

	public Question(Long id, Long studentId, Long courseId, String status, String text) {
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		this.status = status;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
