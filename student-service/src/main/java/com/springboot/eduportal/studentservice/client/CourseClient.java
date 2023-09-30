package com.springboot.eduportal.studentservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.eduportal.studentservice.configuration.CourseClientConfiguration;
import com.springboot.eduportal.studentservice.model.Course;

//@FeignClient(name = "currency-exchange-service", url="http://localhost:8000")
@FeignClient(name = "course-service", configuration = {CourseClientConfiguration.class})
public interface CourseClient {
	
	@GetMapping("/courses")
	public List<Course> getCourses(@RequestParam(name = "ids", required = false) List<Long> ids);
	
	@GetMapping("/courses/{course-id}")
	public Course getCourse(@PathVariable(name = "course-id") Long id);

}
