package com.springboot.eduportal.studentservice.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("junits")
public class CourseCatalogConfigurationTest {

	@Autowired
	CourseCatalogConfiguration courseCatalogConfiguration;
	
//	@BeforeEach
//	public void setProperties() {
//		ReflectionTestUtils.setField(courseCatalogConfiguration, "author", "DC Chand");
//	}
	
	@Value("${course.name}")
	private String courseName;

	@Test
	public void testFindStudentById() {
		assertEquals(courseCatalogConfiguration.getAuthor(), "Buddy");
	}

}
