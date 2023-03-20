package com.mishura.repository;

import com.mishura.DTO.StatisticsDTO;
import com.mishura.config.HibernateUtil;
import com.mishura.model.Workpiece;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class WorkpieceRepository{

    private static WorkpieceRepository instance;

    public static WorkpieceRepository getInstance() {
        if (instance == null) {
            instance = new WorkpieceRepository();
        }
        return instance;
    }

    public void save(Workpiece workpiece) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(workpiece);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Optional<Workpiece> getById(final String id){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Workpiece.class, id));
    }

    public List<Workpiece> getAll(){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return  entityManager.createQuery("from Workpiece", Workpiece.class)
                .getResultList();
    }

    public List<StatisticsDTO> getStatistics(){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery(
                "SELECT new com.mishura.DTO.StatisticsDTO (SUM(w.producedFuel), SUM(w.spentFuel), SUM(w.countOfBrokenChips)) " +
                        "FROM Workpiece w",
                StatisticsDTO.class)
                .getResultList();
    }
}
