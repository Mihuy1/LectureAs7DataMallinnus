package entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Temporal(TemporalType.DATE)
    private Date borrowDate;

    public BorrowedBook() {

    }

    public BorrowedBook(Student student, Book book, Date borrowDate) {
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Override
    public String toString() {
        return "BorrowedBook [id=" + id + ", student=" + student + ", book=" + book + ", borrowDate=" + borrowDate + "]";
    }
}
