package com.springboot.eduportal.studentservice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import com.springboot.eduportal.studentservice.exception.StudentNotFoundException;
import com.springboot.eduportal.studentservice.model.Student;
import com.springboot.eduportal.studentservice.repository.StudentRepository;
import com.springboot.eduportal.studentservice.service.impl.StudentServiceImpl;

@SpringBootTest
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MessageSource messageSource;    
    
    @InjectMocks // auto inject studentRepository
    private StudentService studentService = new StudentServiceImpl();

    @BeforeEach
    void setMockOutput() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(new Student(1L, "user-id", "name", "email@something.com")));
    }	
	@Test
	public void testFindStudentById() {

		// assertNull(studentService.findStudentById(2L), "The student with supplied Id was not found");
		assertThrows(StudentNotFoundException.class, () -> studentService.findStudentById(2L));
		
	}

}
