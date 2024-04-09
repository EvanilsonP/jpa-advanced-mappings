package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDao;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) { SpringApplication.run(CruddemoApplication.class, args); }

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDAO) {
		return runner -> {
			createInstructor(appDAO);
			findInstructor(appDAO);
			removeInstructor(appDAO);
		};
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
