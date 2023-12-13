package com.springboot.eduportal.studentservice.service.impl;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.eduportal.studentservice.model.Register;
import com.springboot.eduportal.studentservice.model.Student;
import com.springboot.eduportal.studentservice.repository.RegisterRepository;
import com.springboot.eduportal.studentservice.service.RegisterService;
import com.springboot.eduportal.studentservice.service.StudentService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private StudentService studentService;

	@Autowired
	private RegisterRepository registerRepository;	

	@Transactional
	@Override
	public void registerStudent(Register register) {
		Student student = studentService.findStudentById(register.getStudentId());
		
		// register.setStudentId(Long.valueOf("WHAT THE HELL IS THIS"));
		registerRepository.save(register);

		String longInvalidName = new Random().ints(300, 66, 95).mapToObj(i -> (char)(i)).map(c -> c.toString()).collect(Collectors.joining());

		student.setName(longInvalidName);
		studentService.saveStudent(student);		
	}

}