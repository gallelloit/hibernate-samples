# hibernate-samples
Plain hibernate samples. Includes database scrpts

# Configuration

* Hibernate 5.3.0
* MySQL Java Connector 8.0.12
* c3p0 Driver 0.9.5.2

# Use case

Simple and plain hibernate samples repository that uses main java classes to test each case.
It includes the majority of the most common Hibernate use cases, such as:

* OneToOne relationship
* OneToMany/ManyToOne relationship
* ManyToMany relationship

From the given samples, it is easy to change configurations and relationships to test diferent FetchTypes(Eager/Lazy), CascadeTyes (unidirectional/bidirectional) and other Hibernate features.

# Getting started

To get this Maven project working:

* Database
  * Install latest MySQL version
  * Execute script 01-create-user.sql
  * Execute script 02-create-db.sql
  
This should create this database:

[Database Diagram]https://github.com/pgbonino/hibernate-samples/blob/master/sql-scripts/hibernate-samples-diagram.png
  
* Java
  * Clone this repo
  * Build using Maven
  * In your IDE, get to the com.gallelloit.hibernate.demo package, and execute each of the proposed Demo Main Apps
  * Play around
  
# Proposed Demo Main Apps

* Demo01CreateInstructorsAndDetailsOneToOne: It creates two instructors with their two details. It uses an One To One Hibernate relationship.
* Demo02AddCoursesToInstructorOneToMany: It adds two courses to a pre-existing user with ID = 1.
* Demo03AddReviewsToCourseOneToMany: It featches a course and adds three reviews to it.
* Demo04AddExistingCourseForNewStudentManyToMany: creates a student and add it to an existing course.
* Demo05AddExistingCourseForExistingStudentManyToMany: It retrieves a student and adds it to an existing course.

In addition to those, you`ll find several Delete* Demo Apps to check the use of cascading on delete.
