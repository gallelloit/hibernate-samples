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
 * It retrieves a student and adds it to an existing course
 * @author pgallello
 *
 */
public class Demo05AddExistingCourseForExistingStudentManyToMany {

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
			
			// Get Student
			int studentId = 1;
			Student theStudent = session.get(Student.class, studentId);
			
			System.out.println("Loaded student: " + theStudent);
			System.out.println("Courses: " + theStudent.getCourses());
			
			int courseId = 10;
			Course theCourse = session.get(Course.class, courseId);

			// Add courses to student
			theCourse.addStudent(theStudent);

			// Saving student
			System.out.println("Adding student ID=1 to course ID=10");
			session.save(theCourse);

			// Commit transaction
			session.getTransaction().commit();
			
			System.out.println("Student added to the course.");
			
			System.out.println("Done!");
			
		} finally {
			session.close();	
			sessionFactory.close();
			
		}
		
	}

}
