package com.springboot.eduportal.studentservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "student-service")
@Component
public class StudentServiceConfiguration {

	private int maxOpenQuestionsPerStudent;

	public int getMaxOpenQuestionsPerStudent() {
		return maxOpenQuestionsPerStudent;
	}

	public void setMaxOpenQuestionsPerStudent(int maxOpenQuestionsPerStudent) {
		this.maxOpenQuestionsPerStudent = maxOpenQuestionsPerStudent;
	}
	
	
}
