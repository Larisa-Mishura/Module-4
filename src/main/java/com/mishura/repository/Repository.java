package com.mishura.repository;

import com.mishura.DTO.StatisticsDTO;
import com.mishura.model.Detail;
import com.mishura.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class Repository {

    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void save(Detail detail) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(detail);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Optional<Detail> getById(final String id){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Detail.class, id));
    }

    public List<Detail> getAll(){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return  entityManager.createQuery("from Detail", Detail.class)
                .getResultList();
    }

    public List<StatisticsDTO> getStatistics(){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery(
                "SELECT new com.mishura.DTO.StatisticsDTO (COUNT(d.id), SUM(d.producedFuel), SUM(d.spentFuel), SUM(d.countOfBrokenChips)) " +
                        "FROM Detail d",
                StatisticsDTO.class)
                .getResultList();
    }
}
