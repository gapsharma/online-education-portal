package com.springboot.eduportal.courseservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.eduportal.courseservice.entity.Course;
import com.springboot.eduportal.courseservice.exception.CourseNotFoundException;
import com.springboot.eduportal.courseservice.repository.CourseRepository;

@RestController
@RequestMapping("/courses")
public class CourseServiceController {
	
	// @Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@PostMapping
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		Course newCourse = courseRepository.save(course);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCourse.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}	

	@GetMapping("/{uuid}")
	public Course getCourseDetails(@PathVariable(name = "uuid") long id){
		System.out.println("This one ?");
		Course course = courseRepository.findById(id).orElseThrow(() -> {
			return new CourseNotFoundException(messageSource.getMessage("error.message.course_not_found", new Object[] {id}, LocaleContextHolder.getLocale()));
		});

		return course;
	}

	// @GetMapping(produces = {"application/xml"})
	@GetMapping
	public List<Course> getCourses(@RequestParam(name = "ids", required = false) List<Long> ids) {
		System.out.println("Or this one ?");
		List<Course> courses = null;
		if (ids == null) {
			courses = courseRepository.findAll();
		} else {
			courses = courseRepository.findByIdIn(ids).get();
		}

		return courses;
	}

	@GetMapping("/technology/{technology}")
	public List<Course> getCoursesByTechnology(@PathVariable String technology) {
		// Exact name
		List<Course> courses = courseRepository.findByTechnology(technology);
		// List<Course> courses = courseRepository.findByTechnologyExact(technology);

		return courses;
	}
	
	@GetMapping("/groupbytechnology")
	public Map<String, List<Course>> groupCoursesByTechnology() {
		List<Course> courses = courseRepository.findAll();
		
		Map<String, List<Course>> courseMap = courses.stream()
		       .collect(Collectors.groupingBy(course -> course.getTechnology()));

		return courseMap;
	}
	
}
