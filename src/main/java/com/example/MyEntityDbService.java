package com.example;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MyEntityDbService {

    final EntityManager entityManager;
    final CriteriaBuilderFactory criteriaBuilderFactory;
    final EntityViewManager entityViewManager;

    @Inject
    public MyEntityDbService(EntityManager entityManager,
                             CriteriaBuilderFactory criteriaBuilderFactory,
                             EntityViewManager entityViewManager) {
        this.entityManager = entityManager;
        this.criteriaBuilderFactory = criteriaBuilderFactory;
        this.entityViewManager = entityViewManager;
    }

    public List<MyEntityView> findAll() {
        return entityViewManager.applySetting(
                EntityViewSetting.create(MyEntityView.class),
                criteriaBuilderFactory.create(entityManager, MyEntity.class)
        ).getResultList();
    }

    public void add(MyEntity myEntity) {
        entityManager.persist(myEntity);
    }

    public MyEntityView find(Long id) {
        return entityViewManager.find(entityManager, MyEntityView.class, id);
    }

}
