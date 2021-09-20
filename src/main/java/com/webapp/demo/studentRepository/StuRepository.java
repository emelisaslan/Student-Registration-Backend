package com.webapp.demo.studentRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.demo.studentEntity.Student;

@Repository
public interface StuRepository extends JpaRepository<Student, Integer> {

}
