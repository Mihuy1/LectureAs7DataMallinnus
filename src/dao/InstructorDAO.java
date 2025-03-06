package dao;

import entity.Instructor;
import entity.TrainingSession;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class InstructorDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("aki");

    public void saveInstructor(Instructor instructor){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(instructor);
        transaction.commit();
    }

    public List<Instructor> findInstructorsBySpecialization(String specialization) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT i FROM Instructor i WHERE i.specialization = :specialization";
        return em.createQuery(jpql, Instructor.class).setParameter("specialization", specialization).getResultList();
    }

    public List<Instructor> findInstructorsByExperience(int experience) {
        EntityManager em = emf.createEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
            Root<Instructor> root = cq.from(Instructor.class);

            cq.select(root).where(cb.equal(root.get("experienceYears"), experience));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
}
