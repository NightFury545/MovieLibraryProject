package com.nightfury.movielibrary.service.impl;

import com.nightfury.movielibrary.model.impl.Director;
import com.nightfury.movielibrary.service.Service;
import com.nightfury.movielibrary.service.jsonhandler.JsonDataReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реалізація сервісу для управління режисерами.
 */
public class DirectorService implements Service<Director> {

    /**
     * Список режисерів.
     */
    private final List<Director> directors;

    /**
     * Конструктор класу DirectorService.
     */
    public DirectorService() {
        this.directors = loadAllDirectors() != null ? loadAllDirectors() : new ArrayList<>();
    }

    /**
     * Завантажує усіх режисерів з JSON файлу.
     *
     * @return Список режисерів.
     */
    private List<Director> loadAllDirectors() {
        return JsonDataReader.readMoviesInfoFile("Director.JSON", Director[].class);
    }

    /**
     * Повертає колекцію режисерів.
     *
     * @return Колекція режисерів.
     */
    public List<Director> getDirectorsCollection() {
        return this.directors;
    }

    /**
     * Знаходить режисера за ідентифікатором.
     *
     * @param id Ідентифікатор режисера.
     * @return Режисер або null, якщо режисера не знайдено.
     */
    @Override
    public Director findById(int id) {
        return directors.stream()
            .filter(director -> director.getId() == id)
            .findFirst()
            .orElse(null);
    }

    /**
     * Знаходить режисерів за ім'ям.
     *
     * @param name Ім'я режисера.
     * @return Список режисерів з вказаним ім'ям.
     */
    @Override
    public List<Director> findByName(String name) {
        return directors.stream()
            .filter(director -> director.getFirstname().equals(name))
            .collect(Collectors.toList());
    }

    /**
     * Видаляє режисера за ідентифікатором.
     *
     * @param id Ідентифікатор режисера.
     */
    @Override
    public void delete(int id) {
        directors.removeIf(director -> director.getId() == id);
    }

    /**
     * Повертає усіх режисерів.
     *
     * @return Список усіх режисерів.
     */
    @Override
    public List<Director> getAll() {
        return new ArrayList<>(this.directors);
    }
}
