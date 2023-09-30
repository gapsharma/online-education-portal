package com.springboot.eduportal.studentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.eduportal.studentservice.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public List<Student> findByUuidIn(List<Long> studentIds);

}
