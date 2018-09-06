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
 * Searches an instructor and deletes it.
 * Since there is a Cascade configuration from Instructor to InstructorDetail,
 * it's not necessary to delete also the InstructorDetail.
 * @author pgallello
 *
 */
public class DeleteInstructorsAndDetailsOneToOneDemo {

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
			
			// Get instructor with primary key
			int instructorId = 1;
			Instructor theInstructor = session.get(Instructor.class, instructorId);
			
			System.out.println("Found instructor: " + theInstructor);
			
			// Delete instructor
			if (theInstructor != null) {
				System.out.println("Deleting instructor...");
				session.delete(theInstructor);
			}
			
			// No need to delete both entities as Cascade.ALL is configured
			
			// Commit transaction
			session.getTransaction().commit();
			
			System.out.println("Instructor (and Detail) deleted.");
			
			System.out.println("Done!");
			
		} finally {
			sessionFactory.close();
			
		}
		
	}

}
