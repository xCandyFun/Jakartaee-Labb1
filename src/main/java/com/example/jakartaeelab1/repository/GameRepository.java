package com.example.jakartaeelab1.repository;

import com.example.jakartaeelab1.entity.Game;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class GameRepository {

    @PersistenceContext
    EntityManager entityManager;


    public List<Game> findAll() {
        var query = entityManager.createQuery("SELECT g FROM Game g");
        return  (List<Game>) query.getResultList();
    }

    public Optional<Game> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(Game.class, id));

    }

    public void insertGame(Game game) {
        entityManager.persist(game);
    }

    public void deleteGame(Long id) {
        var game = findOne(id);
        game.ifPresent((g)-> entityManager .remove(g));
    }

    public List<Game> findAllByName(String name) {
        var query = entityManager.createQuery("SELECT g FROM Game g WHERE g.name Like :name");
        query.setParameter("name",name);
        return  (List<Game>) query.getResultList();
    }

    public void update(Long id, String name) {
        var game = findOne(id);
        game.ifPresent((m) -> m.setName(name));
    }
}
