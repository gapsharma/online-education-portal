package com.springboot.eduportal.studentservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.eduportal.studentservice.exception.StudentNotFoundException;
import com.springboot.eduportal.studentservice.model.Student;
import com.springboot.eduportal.studentservice.repository.StudentRepository;
import com.springboot.eduportal.studentservice.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student findStudentById(long id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> {
			return new StudentNotFoundException(messageSource.getMessage("error.message.student_not_found",
					new Object[] { id }, LocaleContextHolder.getLocale()));
		});

		return student;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

}