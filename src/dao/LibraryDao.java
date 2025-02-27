package dao;

import entity.*;
import jakarta.persistence.*;

import java.util.List;

public class LibraryDao {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Harj1PU");

	public List<Student> getStudents() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Book> getBooks() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("Select b from Book b", Book.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<BorrowedBook> getBorrowedBooks() {
		EntityManager em = emf.createEntityManager();

		try {
			return em.createQuery("SELECT bb FROM BorrowedBook bb", BorrowedBook.class).getResultList();
		} finally {
			em.close();
		}
	}

	public void addStudent(Student student) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void addBook(Book book) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(book);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void addBorrowedBook(BorrowedBook borrowedBook) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(borrowedBook);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void addAuthor(Author author) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(author);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void addBiography(Biography biography) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Author author = biography.getAuthor();
			if (author != null) {
				author = em.merge(author);
				biography.setAuthor(author);
			}
			em.persist(biography);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void removeStudent(Long id) {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Student student = em.find(Student.class, id);
			if (student != null) {
				em.remove(student);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void removeBook(Long id) {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Book book = em.find(Book.class, id);
			if (book != null) {
				em.remove(book);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void removeBorrowedBook(Long id) {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			BorrowedBook borrowedBook = em.find(BorrowedBook.class, id);
			if (borrowedBook != null) {
				em.remove(borrowedBook);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}
