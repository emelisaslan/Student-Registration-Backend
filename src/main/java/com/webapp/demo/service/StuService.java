package com.webapp.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.webapp.demo.studentEntity.Student;


public interface StuService{
	List<Student> showAllStudents();
	Student createStudent(Student student, MultipartFile file);
	Student fetchStudentbyId(int id);
	Student updateStudent(Student student, MultipartFile file);
	void deleteStudent(int id);
}
