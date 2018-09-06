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
 * Creates two instructors with their two details.
 * It uses an One To One Hibernate relationship 
 * @author pgallello
 *
 */
public class Demo01CreateInstructorsAndDetailsOneToOne {

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
			
			// Create objects
			Instructor theInstructor1 = new Instructor("Mary", "Smith", "ms@gallelloit.com");
			InstructorDetail theInstructorDetail1 = new InstructorDetail("Mary's Youtube Cannel", "Guitar");
			
			Instructor theInstructor2 = new Instructor("Javier", "García", "jg@gmail.com");
			InstructorDetail theInstructorDetail2 = new InstructorDetail("Javier's Youtube Channel", "Coding");
			
			// Associate objects together
			theInstructor1.setInstructorDetail(theInstructorDetail1);
			theInstructor2.setInstructorDetail(theInstructorDetail2);
			
			System.out.println("Creating instructors and details...");
			
			// Begin transaction
			session.beginTransaction();
			
			// Save the instructor
			session.save(theInstructor1);
			session.save(theInstructor2);
			
			// Commit transaction
			session.getTransaction().commit();
			
			System.out.println("Instructors and details created");
			
			System.out.println("Done!");
			
		} finally {
			
			sessionFactory.close();
			
		}
		
	}

}
