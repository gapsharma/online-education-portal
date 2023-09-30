package com.springboot.eduportal.studentservice.model;

import java.math.BigDecimal;

public class Course {
	
	private Long id;
	
	private String technology;
	
	private String name;
	
	private String level;
	
	private BigDecimal price;

	public Course() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Course [id=" + id + ", technology=" + technology + ", name=" + name + ", level=" + level + ", price="
				+ price + "]";
	}
	
	

}
