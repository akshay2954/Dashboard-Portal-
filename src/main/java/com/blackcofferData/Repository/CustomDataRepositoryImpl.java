package com.blackcofferData.Repository;

import com.blackcofferData.entity.DataEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CustomDataRepositoryImpl implements CustomDataRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void saveAllEntities(List<DataEntity> entities) {
        for (DataEntity entity : entities) {
            entityManager.persist(entity);
        }
    }

    @Override
    public void saveAll(List<DataEntity> dataList) {
        // Implement saveAll method logic if needed
    }

    @Override
    public List<DataEntity> findAll() {
        TypedQuery<DataEntity> query = entityManager.createQuery("SELECT d FROM DataEntity d", DataEntity.class);
        return query.getResultList();
    }
}
