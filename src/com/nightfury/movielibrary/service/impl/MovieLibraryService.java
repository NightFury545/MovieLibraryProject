package com.nightfury.movielibrary.service.impl;

import com.nightfury.movielibrary.model.impl.MovieLibrary;
import com.nightfury.movielibrary.service.Service;
import com.nightfury.movielibrary.service.jsonhandler.JsonDataReader;
import com.nightfury.movielibrary.service.jsonhandler.JsonDataWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реалізація сервісу для управління бібліотеками фільмів.
 */
public class MovieLibraryService implements Service<MovieLibrary> {

    /**
     * Список бібліотек фільмів.
     */
    private final List<MovieLibrary> movieLibraries;

    /**
     * Конструктор класу MovieLibraryService.
     */
    public MovieLibraryService() {
        this.movieLibraries =
            loadAllMovieLibraries() != null ? loadAllMovieLibraries() : new ArrayList<>();
    }

    /**
     * Завантажує усі бібліотеки фільмів з JSON файлу.
     *
     * @return Список бібліотек фільмів.
     */
    private List<MovieLibrary> loadAllMovieLibraries() {
        return JsonDataReader.readMoviesInfoFile("MovieLibrary.JSON", MovieLibrary[].class);
    }

    /**
     * Додає бібліотеку фільмів.
     *
     * @param movieLibrary Бібліотека фільмів для додавання.
     */
    public void addMovieLibrary(MovieLibrary movieLibrary) {
        this.movieLibraries.add(movieLibrary);
    }

    /**
     * Зберігає бібліотеки фільмів у файл.
     */
    public void saveMovieLibrariesToFile() {
        JsonDataWriter.writeMovieLibraryToFile("MovieLibrary.JSON", this.movieLibraries);
    }

    /**
     * Повертає колекцію бібліотек фільмів.
     *
     * @return Колекція бібліотек фільмів.
     */
    public List<MovieLibrary> getMovieLibrariesCollection() {
        return this.movieLibraries;
    }

    /**
     * Знаходить бібліотеку фільмів за ідентифікатором.
     *
     * @param id Ідентифікатор бібліотеки фільмів.
     * @return Бібліотека фільмів або null, якщо бібліотеку не знайдено.
     */
    @Override
    public MovieLibrary findById(int id) {
        return movieLibraries.stream()
            .filter(library -> library.getId() == id)
            .findFirst()
            .orElse(null);
    }

    /**
     * Знаходить бібліотеки фільмів за назвою.
     *
     * @param name Назва бібліотеки фільмів.
     * @return Список бібліотек фільмів з вказаною назвою.
     */
    @Override
    public List<MovieLibrary> findByName(String name) {
        return movieLibraries.stream()
            .filter(library -> library.getTitle().equals(name))
            .collect(Collectors.toList());
    }

    /**
     * Видаляє бібліотеку фільмів за ідентифікатором.
     *
     * @param id Ідентифікатор бібліотеки фільмів.
     */
    @Override
    public void delete(int id) {
        movieLibraries.removeIf(library -> library.getId() == id);
    }

    /**
     * Повертає усі бібліотеки фільмів.
     *
     * @return Список усіх бібліотек фільмів.
     */
    @Override
    public List<MovieLibrary> getAll() {
        return new ArrayList<>(this.movieLibraries);
    }
}
