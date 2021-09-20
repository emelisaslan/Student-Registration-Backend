package com.webapp.demo.studentController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.demo.service.StuService;
import com.webapp.demo.studentEntity.Student;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("http://localhost:3000")
public class StuController {

	@Autowired
	private StuService service;
	
	public StuController(StuService service) {
		this.service = service;
	}
	
	@RequestMapping(headers=("content-type=multipart/*"),method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Student> createStudent(@RequestParam int id, @RequestParam String name, 
			@RequestParam String surname, @RequestParam String phoneNumber, 
			@RequestParam String city, @RequestParam String district, @RequestParam
			String description, @RequestParam(required = false) MultipartFile file){
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setSurname(surname);
		student.setCity(city);
		student.setDistrict(district);
		student.setPhoneNumber(phoneNumber);
		student.setDescription(description);
		return new ResponseEntity<Student>(service.createStudent(student, file), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public List<Student> showAllStudents(){
		return service.showAllStudents();
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Student> fetchStudentbyId(@PathVariable("id") int Sid){
		return new ResponseEntity<Student>(service.fetchStudentbyId(Sid), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<Student> updateStudent(@RequestParam int id, @RequestParam String name, 
			@RequestParam String surname, @RequestParam String phoneNumber, 
			@RequestParam String city, @RequestParam String district, @RequestParam
			String description, @RequestParam(required = false) MultipartFile file){
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setSurname(surname);
		student.setCity(city);
		student.setDistrict(district);
		student.setPhoneNumber(phoneNumber);
		student.setDescription(description);
		return new ResponseEntity<Student>(service.updateStudent(student, file), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int Sid){
		service.deleteStudent(Sid);
		return new ResponseEntity<String>("Deletion success", HttpStatus.OK);
	}
}
