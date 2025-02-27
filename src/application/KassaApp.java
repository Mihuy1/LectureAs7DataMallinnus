package application;

import dao.LibraryDao;
import entity.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class KassaApp {
	public static void main(String[] args) {
		LibraryDao libraryDao = new LibraryDao();


		Author author1 = new Author();
		author1.setName("J.R.R. Tolkien");

		Biography bio1 = new Biography();
		bio1.setDetails("Author of The Lord of the Rings.");
		bio1.setAuthor(author1);
		author1.setBiography(bio1);

		libraryDao.addAuthor(author1);
		System.out.println("Added Author and Biography: " + author1.getName());

		Book book1 = new Book();
		book1.setTitle("The Hobbit");

		Book book2 = new Book();
		book2.setTitle("Murder on the Orient Express");

		libraryDao.addBook(book1);
		libraryDao.addBook(book2);
		System.out.println("Added Books: " + book1.getTitle() + ", " + book2.getTitle());

		Student student1 = new Student();
		student1.setName("John Doe");

		Student student2 = new Student();
		student2.setName("Emma Watson");

		libraryDao.addStudent(student1);
		libraryDao.addStudent(student2);
		System.out.println("Added Students: " + student1.getName() + ", " + student2.getName());

		// Borrow Books
		BorrowedBook borrowedBook1 = new BorrowedBook();
		borrowedBook1.setStudent(student1);
		borrowedBook1.setBook(book1);
		borrowedBook1.setBorrowDate(new Date());

		BorrowedBook borrowedBook2 = new BorrowedBook();
		borrowedBook2.setStudent(student2);
		borrowedBook2.setBook(book2);
		borrowedBook2.setBorrowDate(new Date());

		libraryDao.addBorrowedBook(borrowedBook1);
		libraryDao.addBorrowedBook(borrowedBook2);
		System.out.println("Borrowed Books recorded.");


	}
}