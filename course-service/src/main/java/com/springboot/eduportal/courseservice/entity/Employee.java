package com.springboot.eduportal.courseservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "EMPLOYEE")
public class Employee {
	
	@Id
	private Long uuid;
	
	private String name;
	
	private BigDecimal salary;
	
	private String department;

	public Employee() {
	}

	public Employee(Long uuid, String name, String department, BigDecimal salary) {
		this.uuid = uuid;
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	
}
