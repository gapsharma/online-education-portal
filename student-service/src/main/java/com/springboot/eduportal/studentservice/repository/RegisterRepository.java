package com.springboot.eduportal.studentservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.eduportal.studentservice.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
	
	public List<Register> findByStudentId(Long studentId);
	
	public List<Register> findByCourseId(Long courseId);	

	public Optional<Register> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
