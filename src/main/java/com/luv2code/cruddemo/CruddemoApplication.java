package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (StudentDAO studentDAO){
		return runner ->{
			createStudent(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Were deleted "+ numRowsDeleted + " students");
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int id = 3;
		studentDAO.delete(id);
	}

	private void updateStudentById(StudentDAO studentDAO) {
		int id= 1;
		Student student = studentDAO.findById(id);
		student.setFirstName("Scooby");
		studentDAO.update(student);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Todd");
		for (Student temp: students){
			System.out.println(temp);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();
		for (Student temp: students){
			System.out.println(temp);
		}
	}


	private void readStudent(StudentDAO studentDAO) {
		Student temp = new Student("Jason", "Todd", "red@batman.com");
		studentDAO.save(temp);
		Student myStudent = studentDAO.findById(temp.getId());
		System.out.println("Student: " + myStudent);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student:");
		Student temp = new Student("Barbara", "Gordon", "barbs@batman.com");
		studentDAO.save(temp);
		System.out.println("Saved student id: "+ temp.getId());
		System.out.println("Creating new student:");
		Student temp2 = new Student("Dick", "Grayson", "dck@batman.com");
		studentDAO.save(temp2);
		System.out.println("Saved student id: "+ temp2.getId());
		System.out.println("Creating new student:");
		Student temp3 = new Student("Jason", "Todd", "red@batman.com");
		studentDAO.save(temp3);
		System.out.println("Saved student id: "+ temp3.getId());
	}

}
