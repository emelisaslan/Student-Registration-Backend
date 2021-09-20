package com.webapp.demo.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.demo.service.StuService;
import com.webapp.demo.studentEntity.Student;
import com.webapp.demo.studentRepository.StuRepository;

@SpringBootTest
public class RepositoryTest {
	
	@MockBean
	StuRepository srepo;
	
	@Autowired
	StuService service;
	
	public static final Student student = new Student(5, "Can", "Aslan", "05242636968", "Ankara" , "Cankaya" ,"New Student");
	public static final Student student2 = new Student(8, "Can", "Aslan", "05242636968", "Ankara" , "Cankaya" ,"New Student");
	
	MultipartFile emptyFile = new MockMultipartFile("fileThatDoesNotExists.jpg",
            "fileThatDoesNotExists.jpg",
            "text/plain",
            "This is a dummy file content".getBytes(StandardCharsets.UTF_8));
	@Test
	public void createStudent() {
		when(srepo.save(student)).thenReturn(student);
		assertEquals(student, service.createStudent(student, emptyFile));
		assertThat(student.getId()).isGreaterThan(0);
		assertThat(student.getSurname()).isNotBlank();
		assertThat(student.getName()).isNotBlank();
		assertThat(student.getCity()).isNotBlank();
		assertThat(student.getDistrict()).isNotBlank();
		assertThat(student.getPhoneNumber()).isNotBlank();
	}
	
	@Test
	public void fetchStudentbyIdTest() {
		Optional<Student> stu = Optional.of(new Student(5, "Can", "Aslan", "05242636968", "Ankara" , "Cankaya" ,"New Student"));
		when(srepo.findById(Mockito.anyInt())).thenReturn(stu);
		assertEquals(stu, Optional.of(service.fetchStudentbyId(student.getId())));
	}
	
	@Test
	public void deleteStudentTest() {
		if(srepo.existsById(student.getId())) {
			service.deleteStudent(student.getId());
			verify(srepo, times(1)).deleteById(student.getId());
		}
		else
			verify(srepo, times(0)).deleteById(student.getId());
	}
	
	@Test
	public void showAllStudentsTest() {
	  List<Student> students = new ArrayList<Student>();
	  students.add(student);
	  students.add(student2);
      when(srepo.findAll()).thenReturn(students);
	  assertEquals(2, service.showAllStudents().size());
	  assertThat(service.showAllStudents().get(0).equals(student));
	}
	
	@Test
	public void updateStudentTest() {
		Optional<Student> stu = Optional.of(new Student(5, "Can", "Aslan", "05242636968", "Ankara" , "Cankaya" ,"New Student"));
		when(srepo.findById(Mockito.anyInt())).thenReturn(stu);
		Student studennt = stu.get();
		studennt.setCity("Tokyo");
		srepo.save(studennt);
		Optional<Student> stuUpdated = srepo.findById(5);
		assertThat(stuUpdated.get().getCity().equals("Tokyo"));
	}
}
