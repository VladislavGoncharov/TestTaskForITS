package com.vladislavgoncharov.testtaskforits.service;

import com.vladislavgoncharov.testtaskforits.entity.EntitySearchSubstring;
import com.vladislavgoncharov.testtaskforits.entity.EntitySemiMagicSquare;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void saveSearchSubstring(String firstArrays, String secondArrays, String result) {

        Session session = entityManager.unwrap(Session.class);
        session.save(new EntitySearchSubstring(firstArrays, secondArrays, result));
    }

    @Override
    public void saveSemiMagicSquare(String originalSquare, String newSemiMagicSquare, int lowestCost, String size) {

        Session session = entityManager.unwrap(Session.class);
        session.save(new EntitySemiMagicSquare(originalSquare, newSemiMagicSquare, lowestCost, size));
    }

    @Override
    public List<?> getAllEntityObject(Class<?> entityClass) {

        Session session = entityManager.unwrap(Session.class);
        List<?> allEntities = session.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        Collections.reverse(allEntities);
        return allEntities;
    }

}
