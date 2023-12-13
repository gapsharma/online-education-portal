package com.springboot.eduportal.courseservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "COURSE")
public class Course {
	
	@Id
	@SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", initialValue = 6, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
	private Long uuid;
	
	@Column()
	private String technology;
	
	private String name;
	
	private String level;
	
	private BigDecimal price;
	
	public Course() {
	}

	public Course(Long id, String technology, String name, String level, BigDecimal price) {
		this.uuid = id;
		this.technology = technology;
		this.name = name;
		this.level = level;
		this.price = price;
	}

	public Long getId() {
		return uuid;
	}

	public void setId(Long id) {
		this.uuid = id;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
