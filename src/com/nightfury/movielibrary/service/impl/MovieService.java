package com.nightfury.movielibrary.service.impl;

import com.nightfury.movielibrary.service.Service;
import com.nightfury.movielibrary.service.jsonhandler.JsonDataReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.nightfury.movielibrary.model.impl.Movie;

/**
 * Реалізація сервісу для управління фільмами.
 */
public class MovieService implements Service<Movie> {

    /**
     * Список фільмів.
     */
    private final List<Movie> movies;

    /**
     * Конструктор класу MovieService.
     */
    public MovieService() {
        this.movies = loadAllMovies() != null ? loadAllMovies() : new ArrayList<>();
    }

    /**
     * Завантажує усі фільми з JSON файлу.
     *
     * @return Список фільмів.
     */
    private List<Movie> loadAllMovies() {
        return JsonDataReader.readMoviesInfoFile("Movie.JSON", Movie[].class);
    }

    /**
     * Повертає колекцію фільмів.
     *
     * @return Колекція фільмів.
     */
    public List<Movie> getMoviesCollection() {
        return this.movies;
    }

    /**
     * Знаходить фільм за ідентифікатором.
     *
     * @param id Ідентифікатор фільму.
     * @return Фільм або null, якщо фільм не знайдено.
     */
    @Override
    public Movie findById(int id) {
        return movies.stream()
            .filter(movie -> movie.getId() == id)
            .findFirst()
            .orElse(null);
    }

    /**
     * Знаходить фільми за назвою.
     *
     * @param name Назва фільму.
     * @return Список фільмів з вказаною назвою.
     */
    @Override
    public List<Movie> findByName(String name) {
        return movies.stream()
            .filter(movie -> movie.getName().equals(name))
            .collect(Collectors.toList());
    }

    /**
     * Видаляє фільм за ідентифікатором.
     *
     * @param id Ідентифікатор фільму.
     */
    @Override
    public void delete(int id) {
        movies.removeIf(movie -> movie.getId() == id);
    }

    /**
     * Повертає усі фільми.
     *
     * @return Список усіх фільмів.
     */
    @Override
    public List<Movie> getAll() {
        return new ArrayList<>(this.movies);
    }
}
