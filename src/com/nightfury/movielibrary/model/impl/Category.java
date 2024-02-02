package com.nightfury.movielibrary.model.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nightfury.movielibrary.model.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє категорію фільмів у системі кінотеатру.
 */
public class Category extends Entity {

    private final String name;
    private final List<Movie> filmscollection;
    private final int[] filmscollectionID;

    /**
     * Конструктор класу Category.
     *
     * @param ID                Ідентифікатор категорії.
     * @param name              Назва категорії.
     * @param filmscollection   Колекція фільмів у категорії.
     * @param filmscollectionID Масив ідентифікаторів фільмів у категорії.
     */
    public Category(@JsonProperty("ID") int ID, @JsonProperty("name") String name,
        @JsonProperty("filmscollection") List<Movie> filmscollection,
        @JsonProperty("filmscollectionID") int[] filmscollectionID) {
        super(ID);
        this.name = name;
        this.filmscollection = filmscollection != null ? filmscollection : new ArrayList<>();
        this.filmscollectionID = filmscollectionID != null ? filmscollectionID : new int[10];
    }

    /**
     * Повертає назву категорії.
     *
     * @return Назва категорії.
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає колекцію фільмів у категорії.
     *
     * @return Список фільмів у категорії.
     */
    public List<Movie> getFilmscollection() {
        return filmscollection;
    }

    /**
     * Повертає масив ідентифікаторів фільмів у категорії.
     *
     * @return Масив ідентифікаторів фільмів у категорії.
     */
    public int[] getFilmscollectionID() {
        return filmscollectionID;
    }

    /**
     * Додає фільм до категорії.
     *
     * @param movie Фільм, який додається до категорії.
     */
    public void addMovieToCategory(Movie movie) {
        filmscollection.add(movie);
    }

    /**
     * Повертає список фільмів у категорії.
     *
     * @return Список фільмів у категорії.
     */
    public List<Movie> getMoviesFromCategory() {
        return filmscollection;
    }

    /**
     * Перевизначений метод toString() для отримання рядкового представлення об'єкта.
     *
     * @return Рядкове представлення об'єкта Category.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nКатегорія: ").append(name);
        sb.append("\nID: '").append(super.ID).append('\'');
        sb.append("\nID фільмів: ");
        for (int i = 0; i < filmscollectionID.length; i++) {
            sb.append(filmscollectionID[i]);
            if (i < filmscollectionID.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
