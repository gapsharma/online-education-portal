package com.springboot.eduportal.studentservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.eduportal.studentservice.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	public Optional<List<Question>> findByStudentIdAndCourseId(Long studentId, Long courseId);

}
