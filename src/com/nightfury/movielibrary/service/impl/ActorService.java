package com.nightfury.movielibrary.service.impl;

import com.nightfury.movielibrary.model.impl.Actor;
import com.nightfury.movielibrary.service.Service;
import com.nightfury.movielibrary.service.jsonhandler.JsonDataReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реалізація сервісу для управління акторами.
 */
public class ActorService implements Service<Actor> {

    /**
     * Список акторів.
     */
    private final List<Actor> actors;

    /**
     * Конструктор класу ActorService.
     */
    public ActorService() {
        this.actors = loadAllActors() != null ? loadAllActors() : new ArrayList<>();
    }

    /**
     * Завантажує усіх акторів з JSON файлу.
     *
     * @return Список акторів.
     */
    private List<Actor> loadAllActors() {
        return JsonDataReader.readMoviesInfoFile("Actor.JSON", Actor[].class);
    }

    /**
     * Повертає колекцію акторів.
     *
     * @return Колекція акторів.
     */
    public List<Actor> getActorsCollection() {
        return this.actors;
    }

    /**
     * Знаходить актора за ідентифікатором.
     *
     * @param id Ідентифікатор актора.
     * @return Актор або null, якщо актора не знайдено.
     */
    @Override
    public Actor findById(int id) {
        return actors.stream()
            .filter(actor -> actor.getId() == id)
            .findFirst()
            .orElse(null);
    }

    /**
     * Знаходить акторів за ім'ям.
     *
     * @param name Ім'я актора.
     * @return Список акторів з вказаним ім'ям.
     */
    @Override
    public List<Actor> findByName(String name) {
        return actors.stream()
            .filter(actor -> actor.getFirstname().equals(name))
            .collect(Collectors.toList());
    }

    /**
     * Видаляє актора за ідентифікатором.
     *
     * @param id Ідентифікатор актора.
     */
    @Override
    public void delete(int id) {
        actors.removeIf(actor -> actor.getId() == id);
    }

    /**
     * Повертає усіх акторів.
     *
     * @return Список усіх акторів.
     */
    @Override
    public List<Actor> getAll() {
        return new ArrayList<>(this.actors);
    }
}
