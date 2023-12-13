package com.springboot.eduportal.courseservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eduportal.courseservice.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	public List<Course> findByTechnology(String technology);
	
	public List<Course> findByTechnologyStartingWith(String technology);

	public List<Course> findByTechnologyContaining(String technology);

	//public List<Course> findByTechnologyExact(String technology);
	
	public Optional<List<Course>> findByIdIn(List<Long> courseIds);

}
