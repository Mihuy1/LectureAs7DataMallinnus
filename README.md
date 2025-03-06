# Akido Training Management System
A Java-based management system for tracking students, instructors, training sessions, attendance, and progress reports at an Akido training center.
Features
- Student management and progress tracking
- Instructor profiles with specialization information
- Training session scheduling and attendance tracking
- Progress reports with optimistic locking for concurrent updates
- Various search capabilities (by rank, experience, location, etc.)

## Technology Stack
- Java 13
- Maven
- Jakarta Persistence API (JPA) 3.0
- Hibernate 6.0.0.Final
- MySQL 8.0

## Setup Instructions
### Prerequisites
- Java 13 JDK installed
- Maven installed
- MySQL 8.0 server running
- Jakarta

## Database Setup

1.  Create a MySQL database named akido:
```
CREATE DATABASE akido;
```

2. Update the database connection properties in src/META-INF/persistence.xml if needed:

```
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/akido"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="secret"/>
```
