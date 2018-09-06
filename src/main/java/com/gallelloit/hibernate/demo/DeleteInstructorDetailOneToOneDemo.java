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
 * Deletes instructor detail. It doesn't delete the instructor itself.
 * @author pgallello
 *
 */
public class DeleteInstructorDetailOneToOneDemo {

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
			
			// Get instructor detail with primary key
			int instructorDetailId = 2;
			InstructorDetail theInstructorDetail = session.get(InstructorDetail.class, instructorDetailId);
			
			System.out.println("Found instructor detail: " + theInstructorDetail);
			
			// Fetch Instructor and show it
			System.out.println("Found instructor: " + theInstructorDetail.getInstructor().toString());
			
			// Delete instructor detail (associated instructor won't be deleted)
			System.out.println("Deleting instructor detail...");
			theInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(theInstructorDetail);
			
			// Commit transaction
			session.getTransaction().commit();
			
			System.out.println("Instructor detail (not instructor) deleted.");
			
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Handle connection leak
			session.close();
			sessionFactory.close();
		}
		
	}

}
