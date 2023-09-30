package com.springboot.eduportal.studentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "REGISTER")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"studentId", "courseId"})})
public class Register {
	
	@Id
	@SequenceGenerator(name = "register_sequence", sequenceName = "register_sequence", initialValue = 30, allocationSize = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "register_sequence")
	private Long uuid;
	
	private Long studentId;
	
	private Long courseId;
	
	private boolean premium;

	public Register() {
	}

	public Register(Long uuid, Long studentId, Long courseId, boolean premium) {
		this.uuid = uuid;
		this.studentId = studentId;
		this.courseId = courseId;
		this.premium = premium;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
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

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

}
