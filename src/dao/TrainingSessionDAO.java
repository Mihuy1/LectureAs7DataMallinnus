package dao;

import entity.Student;
import entity.TrainingSession;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class TrainingSessionDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("aki");

    public void saveTrainingSession(TrainingSession trainingSession) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(trainingSession);
        em.getTransaction().commit();
    }

    public List<TrainingSession > findTrainingSessionByStudentId(Long studentId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT ts FROM TrainingSession ts JOIN ts.attendances a WHERE a.student.id = :studentId";
        return em.createQuery(jpql, TrainingSession.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

    public List<TrainingSession> findTrainingSessionByLocation(String location) {
        EntityManager em = emf.createEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TrainingSession> cq = cb.createQuery(TrainingSession.class);
            Root<TrainingSession> root = cq.from(TrainingSession.class);

            cq.select(root).where(cb.equal(root.get("location"), location));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }

}
