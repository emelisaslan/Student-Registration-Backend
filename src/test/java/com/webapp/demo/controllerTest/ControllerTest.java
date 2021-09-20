package com.webapp.demo.controllerTest;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.demo.service.StuService;
import com.webapp.demo.studentController.StuController;
import com.webapp.demo.studentEntity.Student;
import com.webapp.demo.studentRepository.StuRepository;

import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(StuController.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StuService service;

	@MockBean
	private StuRepository srepo;

	public Student student = new Student(5, "Can", "Aslan", "05242636968", "Ankara", "Cankaya", "New Student");
	public Student student2 = new Student(8, "Canan", "Ay", "052426", "Washington", "", "New Student");

	MultipartFile emptyFile = new MockMultipartFile("fileThatDoesNotExists.jpg", "fileThatDoesNotExists.jpg",
			"text/plain", "This is a dummy file content".getBytes(StandardCharsets.UTF_8));
	
	@Autowired
	ObjectMapper objm = new ObjectMapper();

	@Test
	public void fetchStudentbyIdTest() throws Exception {

		when(service.fetchStudentbyId(Mockito.anyInt())).thenReturn(student);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/students/5")).andExpect(jsonPath("$", notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Can"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Aslan"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("05242636968"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Ankara"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.district").value("Cankaya"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("New Student"))
				.andExpect(status().isOk());
	}

	@Test
	public void createStudentTest() throws Exception {

		when(service.createStudent(Mockito.any(Student.class), Mockito.any(MultipartFile.class))).thenReturn(student);
		//student.setPhoto(emptyFile.getOriginalFilename());

		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("id", "5");
		requestParams.add("name", "john");
		requestParams.add("surname", "blabla");
		requestParams.add("city", "somewhere");
		requestParams.add("district", "somewhere");
		requestParams.add("phoneNumber", "0555555555");
		requestParams.add("description", "description");
		mockMvc.perform(post("/api/students").contentType(MediaType.MULTIPART_FORM_DATA).params(requestParams))
				.andExpect(status().isCreated());

	}

	@Test
	public void createStudentWithNullID() throws Exception {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("id", "");
		requestParams.add("name", "john");
		requestParams.add("surname", "blabla");
		requestParams.add("city", "somewhere");
		requestParams.add("district", "somewhere");
		requestParams.add("phoneNumber", "0555555555");
		requestParams.add("description", "description");
		mockMvc.perform(post("/api/students").contentType(MediaType.MULTIPART_FORM_DATA).params(requestParams))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void showAllStudentsTest() throws JsonProcessingException, Exception {
		List<Student> students = new ArrayList<Student>();
		students.add(student);
		students.add(student2);
		when(service.showAllStudents()).thenReturn(students);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/students").contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[1].city", is("Washington")))
				.andExpect(jsonPath("$[0].name", is("Can"))).andExpect(status().isOk());

	}

	@Test
	public void updateStudentTest() throws Exception {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("id", "5");
		requestParams.add("name", "john");
		requestParams.add("surname", "blabla");
		requestParams.add("city", "somewhere");
		requestParams.add("district", "somewhere");
		requestParams.add("phoneNumber", "0555555555");
		requestParams.add("description", "description");
		mockMvc.perform(put("/api/students").contentType(MediaType.MULTIPART_FORM_DATA).params(requestParams))
				.andExpect(status().isOk());
	}

	@Test
	public void updateStudentWithNullID() throws Exception {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("id", "");
		requestParams.add("name", "john");
		requestParams.add("surname", "blabla");
		requestParams.add("city", "somewhere");
		requestParams.add("district", "somewhere");
		requestParams.add("phoneNumber", "0555555555");
		requestParams.add("description", "description");
		mockMvc.perform(put("/api/students").contentType(MediaType.MULTIPART_FORM_DATA).params(requestParams))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteStudentTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/students/100"))
		.andExpect(status().isOk()).andReturn();
	}

}
