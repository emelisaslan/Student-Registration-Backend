package com.webapp.demo.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.demo.exception.InvalidRequestException;
import com.webapp.demo.exception.StudentNotFoundException;
import com.webapp.demo.exception.StudentAlreadyExistsException;
import com.webapp.demo.service.StuService;
import com.webapp.demo.studentEntity.Student;
import com.webapp.demo.studentRepository.StuRepository;

@Service
public class StuServiceImpl implements StuService {

	@Autowired
	StuRepository repository;

	@Autowired
	public StuServiceImpl(StuRepository repository) {
		super();
		this.repository = repository;
	}

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webApp/imagedata";

	@Override
	public Student createStudent(Student student, MultipartFile file) {
		Student stu = repository.findById(student.getId()).orElse(null);

		if (stu != null)
			throw new StudentAlreadyExistsException(student.getId());

		if (student == null || student.getId() <= 0 || (Object) student.getId() == null)
			throw new InvalidRequestException();

		if (file != null) {
			String fileName = student.getId() + file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDirectory, fileName);

			try {
				Files.write(fileNameAndPath, file.getBytes());
				student.setPhoto(fileName);
			} catch (IOException e) {
				// TODO Auto-gen erated catch block
				throw new InvalidRequestException();
			}
		}

		return repository.save(student);
	}

	@Override
	public List<Student> showAllStudents() {
		return repository.findAll();
	}

	@Override
	public Student fetchStudentbyId(int id) {
		Optional<Student> student = repository.findById(id);
		if (student.isPresent()) {
			return student.get();
		} else
			throw new StudentNotFoundException("id", id);

	}

	@Override
	public Student updateStudent(Student student, MultipartFile file) {
		if (student == null || student.getId() <= 0 || (Object) student.getId() == null) {
			throw new InvalidRequestException();
		}

		Optional<Student> stu = repository.findById(student.getId());
		if (stu.isEmpty()) {
			throw new StudentNotFoundException("id", student.getId());
		}

		Student st = stu.get();
		st.setName(student.getName());
		st.setSurname(student.getSurname());
		st.setCity(student.getCity());
		st.setDescription(student.getDescription());
		st.setDistrict(student.getDistrict());
		st.setPhoneNumber(student.getPhoneNumber());

		if (file != null) {
			String fileName = student.getId() + file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDirectory, fileName);

			try {
				Files.write(fileNameAndPath, file.getBytes());
				st.setPhoto(fileName);
			} catch (IOException e) {
				// TODO Auto-gen erated catch block
				throw new InvalidRequestException();
			}
		}
		return repository.save(st);
	}

	@Override
	public void deleteStudent(int id) {
		repository.findById(id).orElseThrow(() -> new StudentNotFoundException("id", id));

		repository.deleteById(id);
	}

}
