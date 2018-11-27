package com.junying.Daos;

import javax.persistence.EntityManager;

public class TranscationDao {
    EntityManager entityManager;

    public TranscationDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
