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
 * It deletes a course. It doesn't delete the instructor nor the students it has associated.
 * @author pgallello
 *
 */
public class DeleteCourseDemo {

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
			
			// Retrieve Instructor with id = 1
			int theId = 10;
			Course theCourse = session.get(Course.class, theId);
			
			System.out.println("Deleting course " + theCourse + "...");
			
			session.delete(theCourse);
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Course deleted.");
			
			System.out.println("Done!");
			
		} finally {
			session.close();	
			sessionFactory.close();
			
		}
		
	}

}
