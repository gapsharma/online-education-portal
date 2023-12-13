package com.springboot.eduportal.studentservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseCatalogConfiguration {

	@Value(value = "${course.name}")
	private String courseName;

	@Value(value = "${course.author}")
	private String author;

	public String getCourseName() {
		return courseName;
	}

	public String getAuthor() {
		return author;
	}

}
