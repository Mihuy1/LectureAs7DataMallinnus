package dao;

import entity.Attendance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AttendanceDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("aki");

    public void saveAttendance(Attendance attendance){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(attendance);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
