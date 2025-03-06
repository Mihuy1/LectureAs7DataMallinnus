package application;

import dao.AttendanceDAO;
import dao.InstructorDAO;
import dao.StudentDAO;
import dao.TrainingSessionDAO;
import entity.*;

import java.time.LocalDateTime;
import java.util.List;

public class KassaApp {
	public static void main(String[] args) {
		StudentDAO studentDAO = new StudentDAO();

		Student student1 = new Student("John Doe", "john@example.com", "rank 2", LocalDateTime.now());
		Student student2 = new Student("Jane Doe", "jane@example.com", "rank 3", LocalDateTime.now());

		ProgressReport progressReport1 = new ProgressReport(LocalDateTime.now(), "Achievements1", "Areas for improvement");
		ProgressReport progressReport2 = new ProgressReport(LocalDateTime.now(), "Achievements2", "Areas for improvement");

		progressReport1.setStudent(student1);
		progressReport2.setStudent(student2);

		student1.getProgressReports().add(progressReport1);
		student2.getProgressReports().add(progressReport2);

		studentDAO.saveStudent(student1, progressReport1);
		studentDAO.saveStudent(student2, progressReport2);

		System.out.println("Students with rank 2:");
		studentDAO.findStudentsByRank("rank 2").forEach(System.out::println);
		System.out.println("Students with recent progress reports:");
		studentDAO.findStudentsWithRecentProgressReports(LocalDateTime.now().minusDays(1)).forEach(System.out::println);

		// Create an Instructor
		Instructor instructor = new Instructor("Instructor 1", "something 1", 5);

		// Create a TrainingSession
		TrainingSession trainingSession = new TrainingSession(LocalDateTime.now(), "Location 1", 60);

		// Set the Instructor reference in the TrainingSession
		trainingSession.setInstructor(instructor);

		// Add the TrainingSession to the Instructor's set of training sessions
		instructor.getTrainingSessions().add(trainingSession);

		// Save the Instructor (which will also save the TrainingSession due to cascade settings)
		InstructorDAO instructorDAO = new InstructorDAO();
		instructorDAO.saveInstructor(instructor);

		System.out.println("Instructor has been saved successfully");
		instructorDAO.findInstructorsBySpecialization("something 1").forEach(System.out::println);

		Attendance attendance = new Attendance(AttendanceStatus.PRESENT, "Good");
		attendance.setStudent(student1);
		attendance.setTrainingSession(trainingSession);

		student1.getAttendances().add(attendance);
		trainingSession.getAttendances().add(attendance);

		AttendanceDAO attendanceDAO = new AttendanceDAO();

		attendanceDAO.saveAttendance(attendance);

		// Find training sessions attended by student
		TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO();
		List<TrainingSession> trainingSessions = trainingSessionDAO.findTrainingSessionByStudentId(student1.getId());
		System.out.println("Training sessions attended by student:");
		trainingSessions.forEach(System.out::println);

		studentDAO.findStudentsJoinDate(LocalDateTime.now()).forEach(System.out::println);

		trainingSessionDAO.findTrainingSessionByLocation("location 1").forEach(System.out::println);

		instructorDAO.findInstructorsByExperience(5).forEach(System.out::println);

	}
}