package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDao;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) { SpringApplication.run(CruddemoApplication.class, args); }

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDAO) {
		return runner -> {
			createInstructor(appDAO);
			findInstructor(appDAO);
			removeInstructor(appDAO);
			findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);
			createInstructorWithCourses(appDAO);
			findInstructorWithCourses(appDAO);
			findCoursesForInstructor(appDAO);
			findInsturctorCoursesJOINFETCH(appDAO);
			updateInstructor(appDAO);
			updateCourse(appDAO);
			deleteInstructor(appDAO);
			deleteCourse(appDAO);
			createCourseReviews(appDAO);
			retrieveCoursesAndReviews(appDAO);
            deleteCoursesAndReviews(appDAO);
			creaeCourseAndStudents(appDAO);
			findCoursesAndStudents(appDAO);
		};
	}

	private void findCoursesAndStudents(AppDao appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseStudentByCourseId(theId);
		
	}

	private void creaeCourseAndStudents(AppDao appDAO) {
		Course tempCourse = new Course("Pacman - How to score");
		Student tempStudent = new Student("Mary", "Doe", "none");
		tempCourse.addStudent(tempStudent);
		appDAO.save(tempCourse);
	}

	private void deleteCoursesAndReviews(AppDao appDAO) {
		int theId = 10;
		appDAO.deleteCourseById(theId);
	}

	private void retrieveCoursesAndReviews(AppDao appDAO) {
		int theId = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(theId);
	}

	private void createCourseReviews(AppDao appDAO) {
		Course tempCourse = new Course("Pacman - How to score one million points");
		tempCourse.add(new Review("Great course!!!"));
		appDAO.save(tempCourse);
	}

	private void deleteCourse(AppDao appDAO) {
		int theId = 10;
		appDAO.deleteCourseById(theId);
	}

	private void deleteInstructor(AppDao appDAO) {
		int theId = 1;
		appDAO.deleteInstructorDetailById(theId);
	}

	private void updateCourse(AppDao appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseById(theId);
		tempCourse.setTitle("Enjoy");
		appDAO.update(tempCourse);
	}

	private void updateInstructor(AppDao appDAO) {
		int theId = 1;
		// find the instructor
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		tempInstructor.setLastName("TIGER");
		appDAO.update(tempInstructor);
	}

	private void findInsturctorCoursesJOINFETCH(AppDao appDAO) {
		int theId = 1;
		// find the instructor
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
	}

	private void findCoursesForInstructor(AppDao appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findInstructorById(theId);
		// find courses for instructor
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
	}

	private void findInstructorWithCourses(AppDao appDAO) {
		int theId = 1;
		Instructor tempInstructor = appDAO.findInstructorById(theId);
	}

	private void createInstructorWithCourses(AppDao appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Susan", "Public", "luv2code@gmail.com");
		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("www.susan.com/youtube", "learn how to swim");
		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		// create some courses
		Course tempCourse1 = new Course("Air Guitar");
		Course tempCourse2 = new Course("Samba");
		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		// save the instructor
		appDAO.save(tempInstructor);
	}

	private void deleteInstructorDetail(AppDao appDAO) {
		int theId = 2;
		appDAO.deleteInstructorDetailById(theId);
	}

	private void findInstructorDetail(AppDao appDAO) {
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
	}

	private void removeInstructor(AppDao appDAO) {
		int theId = 1;
		appDAO.deleteInstructorById(theId);
	}

	private void findInstructor(AppDao appDAO) {
		int theId = 1;
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("Found one instructor by id: " + theId);
		System.out.println("Instructor name is: " + tempInstructor);
		System.out.println("The associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Chad", "Darby", "luv2code@gmail.com");
		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("www.luv2code.com/youtube", "luv 2 code");
		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		// save instructor to the database / this'll also save the details object
		System.out.println("Saved instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
	}
}
