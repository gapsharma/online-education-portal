package com.springboot.eduportal.studentservice.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.CacheControlConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.eduportal.studentservice.client.CourseClient;
import com.springboot.eduportal.studentservice.configuration.CourseCatalogConfiguration;
import com.springboot.eduportal.studentservice.configuration.StudentServiceConfiguration;
import com.springboot.eduportal.studentservice.exception.NotPremiumMemberException;
import com.springboot.eduportal.studentservice.exception.OpenQuestionsAllowedExceededException;
import com.springboot.eduportal.studentservice.exception.StudentNotEnrolledToCourseException;
import com.springboot.eduportal.studentservice.exception.StudentNotFoundException;
import com.springboot.eduportal.studentservice.model.Course;
import com.springboot.eduportal.studentservice.model.Question;
import com.springboot.eduportal.studentservice.model.Register;
import com.springboot.eduportal.studentservice.model.Student;
import com.springboot.eduportal.studentservice.repository.QuestionRepository;
import com.springboot.eduportal.studentservice.repository.RegisterRepository;
import com.springboot.eduportal.studentservice.repository.StudentRepository;
import com.springboot.eduportal.studentservice.service.RegisterService;
import com.springboot.eduportal.studentservice.service.Vehicle;

import feign.FeignException;

@RestController
@RequestMapping(path = "/students")
public class StudentServiceController {

	// @Autowired
	private MessageSource messageSource;
	
	private Vehicle vehicle;

	// @Autowired
	private StudentRepository studentRepository;

	// @Autowired
	private RegisterRepository registerRepository;

	// @Autowired
	private QuestionRepository questionRepository;

	// @Autowired
	private CourseClient courseClient;

	// @Autowired
	private StudentServiceConfiguration serviceConfiguration;
	
	// @Autowired
	RegisterService registerService;
	
	// @Autowired
	CourseCatalogConfiguration courseCatalogConfiguration;
	
	// @Autowired
	public void setVehicle(Vehicle v) {
		this.vehicle = v;
	}
	
	protected StudentServiceController(MessageSource messageSource, Vehicle vehicle,
			StudentRepository studentRepository, RegisterRepository registerRepository,
			QuestionRepository questionRepository, CourseClient courseClient,
			StudentServiceConfiguration serviceConfiguration, RegisterService registerService,
			CourseCatalogConfiguration courseCatalogConfiguration) {
		super();
		this.messageSource = messageSource;
		this.vehicle = vehicle;
		this.studentRepository = studentRepository;
		this.registerRepository = registerRepository;
		this.questionRepository = questionRepository;
		this.courseClient = courseClient;
		this.serviceConfiguration = serviceConfiguration;
		this.registerService = registerService;
		this.courseCatalogConfiguration = courseCatalogConfiguration;
	}

	@GetMapping("/coursecatalog")
	public String getCourseCatalog() {
		return "The course " + courseCatalogConfiguration.getCourseName() + " is authored by " + courseCatalogConfiguration.getAuthor();
	}

	@PostMapping()
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getUuid())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public List<Student> getAllStudents() {
		List<Student> students = studentRepository.findAll();

		return students;
	}

	@GetMapping("/{student-id}")
	public Student getStudentDetails(@PathVariable(name = "student-id") long id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> {
			return new StudentNotFoundException(messageSource.getMessage("error.message.student_not_found",
					new Object[] { id }, LocaleContextHolder.getLocale()));
		});

		return student;
	}

	@PostMapping("/register")
	public ResponseEntity<Register> registerStudentToACourse(@RequestBody Register register) {
		try {
			courseClient.getCourse(register.getCourseId());
		} catch (FeignException fex) {
			throw fex;
		}
		
		registerService.registerStudent(register);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/register/students/{student-id}/courses/{course-id}")
	public ResponseEntity<Register> updateRegisterForAStudent(@RequestBody Register register,
			@PathVariable(name = "student-id") Long studentId, @PathVariable(name = "course-id") Long courseId) {
		Register savedRecord = registerRepository.findByStudentIdAndCourseId(studentId, courseId).orElseThrow();

		savedRecord.setPremium(register.isPremium());

		registerRepository.save(savedRecord);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/courses/{course-id}")
	public List<Student> getStudentsEnrolledToACourse(@PathVariable(name = "course-id") Long courseId) {

		List<Register> registers = registerRepository.findByCourseId(courseId);
		List<Long> studentIds = registers.stream().map(reg -> reg.getStudentId()).collect(Collectors.toList());

		List<Student> students = studentRepository.findByUuidIn(studentIds);

		return students;
	}

	@GetMapping("{student-id}/courses")
	public List<Course> getCoursesEnrolledByAStudent(@PathVariable(name = "student-id") Long studentId) {

		List<Register> registers = registerRepository.findByStudentId(studentId);
		List<Long> courseIds = registers.stream().map(reg -> reg.getCourseId()).collect(Collectors.toList());

		List<Course> courses = courseClient.getCourses(courseIds);
		return courses;
	}

	@PostMapping("{student-id}/courses/{course-id}/questions")
	public ResponseEntity<Question> postQuestionByStudent(@PathVariable(name = "student-id") Long studentId,
			@PathVariable(name = "course-id") Long courseId, @RequestBody Question question) {
		Register student = registerRepository.findByStudentIdAndCourseId(studentId, courseId).orElseThrow(() -> {
			return new StudentNotEnrolledToCourseException(
					messageSource.getMessage("error.message.student_not_enrolled_to_course",
							new Object[] { studentId, courseId }, LocaleContextHolder.getLocale()));
		});

		if (!student.isPremium()) {
			throw new NotPremiumMemberException(messageSource.getMessage("error.message.not_premium_member",
					new Object[] { studentId, courseId }, LocaleContextHolder.getLocale()));
		}

		long noOfquestionsPostedByStudent = questionRepository.findByStudentIdAndCourseId(studentId, courseId).get()
				.stream().count();
		if (noOfquestionsPostedByStudent == serviceConfiguration.getMaxOpenQuestionsPerStudent()) {
			throw new OpenQuestionsAllowedExceededException(
					messageSource.getMessage("error.message.open_questions_allowed_exceeded",
							new Object[] { studentId, courseId }, LocaleContextHolder.getLocale()));
		}

		question.setCourseId(courseId);
		question.setStudentId(studentId);
		Question savedQuestion = questionRepository.save(question);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedQuestion.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping("{student-id}/courses/{course-id}/questions")
	public List<Question> getQuestionsByStudent(@PathVariable(name = "student-id") Long studentId,
			@PathVariable(name = "course-id") Long courseId) {

		return questionRepository.findByStudentIdAndCourseId(studentId, courseId)
				.orElseGet(() -> new ArrayList<Question>());
	}

}
