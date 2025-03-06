package dao;

import entity.ProgressReport;
import entity.Student;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class StudentDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("aki");

    public void saveStudent(Student student, ProgressReport progressReport) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            progressReport.setStudent(student);
            em.persist(progressReport);
            em.getTransaction().commit();
        }
        catch (OptimisticLockException e) {
            em.getTransaction().rollback();
            throw new OptimisticLockException("This report was updated by someone else. Please refresh and try again.");
        }

        finally {
            em.close();
        }

    }

    public void saveProgressReport(ProgressReport progressReport) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(progressReport);
        em.getTransaction().commit();
    }

    public List<Student> findStudentsByRank(String rank) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT s FROM Student s WHERE s.rank = :rank";
        return em.createQuery(jpql, Student.class)
                .setParameter("rank", rank)
                .getResultList();
    }

    public List<Student> findStudentsWithRecentProgressReports(LocalDateTime localDateTime) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT s FROM Student s JOIN s.progressReports pr WHERE pr.reportDate < :localDateTime";
        return em.createQuery(jpql, Student.class)
                .setParameter("localDateTime", localDateTime)
                .getResultList();
    }

    public List<Student> findStudentsJoinDate(LocalDateTime localDateTime) {
        EntityManager em = emf.createEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Student> cq = cb.createQuery(Student.class);
            Root<Student> student = cq.from(Student.class);

            LocalDateTime sixMonthsAgo = LocalDateTime.now().minus(6, ChronoUnit.MONTHS);

            cq.select(student)
                    .where(cb.greaterThanOrEqualTo(student.get("joinDate"), sixMonthsAgo));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
}
