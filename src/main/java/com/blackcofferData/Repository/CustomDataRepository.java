package com.blackcofferData.Repository;

import com.blackcofferData.entity.DataEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomDataRepository {
    void saveAllEntities(List<DataEntity> entities);

    void saveAll(List<DataEntity> dataList);

    List<DataEntity> findAll();
}