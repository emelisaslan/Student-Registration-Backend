package com.webapp.demo;

import java.io.File;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.webapp.demo.serviceImpl.StuServiceImpl;


@SpringBootApplication
public class StudentRegistrationSystemApplication {

	public static void main(String[] args) throws SQLException {
		
		new File(StuServiceImpl.uploadDirectory).mkdir();
		SpringApplication.run(StudentRegistrationSystemApplication.class, args);
		
		
	}

}
