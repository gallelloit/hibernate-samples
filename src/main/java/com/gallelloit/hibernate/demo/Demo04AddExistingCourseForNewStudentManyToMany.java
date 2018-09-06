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
 * It creates a student and add it to an existing course
 * @author pgallello
 *
 */
public class Demo04AddExistingCourseForNewStudentManyToMany {

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
			
			// Create student
			Student theStudent = new Student("Robert", "Dale", "jd@gmail.com");
			System.out.println("Student created (so far just in memory): " + theStudent);
			
			int courseId = 11;
			Course theCourse = session.get(Course.class, courseId);
			System.out.println("Course loaded: " + theCourse);

			// Add courses to student
			theCourse.addStudent(theStudent);

			// Saving student
			System.out.println("Saving the student...");
			session.save(theStudent);

			// Commit transaction
			session.getTransaction().commit();
			
			System.out.println("Student created and added to the retrieved course");
			
			System.out.println("Done!");
			
		} finally {
			session.close();	
			sessionFactory.close();
			
		}
		
	}

}
