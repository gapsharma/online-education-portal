package com.springboot.eduportal.courseservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eduportal.courseservice.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	public List<Course> findByTechnology(String technology);
	
	public Optional<List<Course>> findByIdIn(List<Long> courseIds);

}
