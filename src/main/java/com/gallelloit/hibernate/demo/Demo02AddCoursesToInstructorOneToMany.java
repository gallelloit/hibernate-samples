package com.gallelloit.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gallelloit.hibernate.demo.entity.Course;
import com.gallelloit.hibernate.demo.entity.Instructor;
import com.gallelloit.hibernate.demo.entity.InstructorDetail;
import com.gallelloit.hibernate.demo.entity.Review;
import com.gallelloit.hibernate.demo.entity.Student;

/**
 * It adds two courses to a pre-existing user with ID = 1
 * @author pgallello
 *
 */
public class Demo02AddCoursesToInstructorOneToMany {

	public static void main(String[] args) {
		
		// Create session factory
		
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create a session

		Session session = sessionFactory.getCurrentSession();
		
		try {
			// Begin transaction
			session.beginTransaction();
			
			// Create the instructor from db
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
				
			// Create courses
			Course theCourse1 = new Course("Spring Course");
			Course theCourse2 = new Course("Hibernate Course");

			instructor.addCourse(theCourse1);
			instructor.addCourse(theCourse2);
			
			System.out.println("Adding two courses to instructor...");

			session.save(theCourse1);
			session.save(theCourse2);
			
			// Commit transaction
			session.getTransaction().commit();
			
			System.out.println("Courses added to instructor.");
			
			System.out.println("Done!");
			
		} finally {
			session.close();	
			sessionFactory.close();
			
		}
		
	}

}
