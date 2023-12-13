package com.springboot.eduportal.studentservice.service;

import com.springboot.eduportal.studentservice.model.Student;

public interface StudentService {
	
	Student findStudentById(long id);
	
	void saveStudent(Student student);
	
}
