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
 * It featches a course and adds three reviews to it 
 * @author pgallello
 *
 */
public class Demo03AddReviewsToCourseOneToMany {

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

			// Fetch course with id = 10
			int courseId = 10;
			Course theCourse = session.get(Course.class, courseId);
			
			// Add some reviews
			theCourse.add(new Review("Great course!"));
			theCourse.add(new Review("Bad course!"));
			theCourse.add(new Review("So so course!"));
			
			// Save the course
			System.out.println("Saving the course..." + theCourse);
			System.out.println("With this reviews: " + theCourse.getReviews());
			session.save(theCourse);
			
			// Commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			session.close();	
			sessionFactory.close();
			
		}
		
	}

}
