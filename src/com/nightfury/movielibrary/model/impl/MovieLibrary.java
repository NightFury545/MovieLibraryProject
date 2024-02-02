package com.nightfury.movielibrary.model.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nightfury.movielibrary.model.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє бібліотеку фільмів користувача в системі кінотеатру.
 */
public class MovieLibrary extends Entity {

    @JsonIgnore
    private final List<Movie> favoritefilms;

    private List<Integer> favoritefilmsID;
    @JsonIgnore
    private User user;
    private final int userID;
    private String title;

    /**
     * Конструктор класу MovieLibrary.
     *
     * @param ID    Ідентифікатор бібліотеки фільмів.
     * @param title Назва бібліотеки фільмів.
     * @param user  Користувач, якому належить бібліотека фільмів.
     */
    public MovieLibrary(int ID, String title, User user) {
        super(ID);
        this.favoritefilms = new ArrayList<>();
        this.title = title;
        this.user = user;
        this.userID = user != null ? this.user.getId() : 0;
        this.favoritefilmsID = new ArrayList<>();
    }

    /**
     * Конструктор класу MovieLibrary для десеріалізації з JSON.
     *
     * @param ID    Ідентифікатор бібліотеки фільмів.
     * @param title Назва бібліотеки фільмів.
     */
    @JsonCreator
    public MovieLibrary(@JsonProperty("id") int ID, @JsonProperty("title") String title) {
        super(ID);
        this.favoritefilms = new ArrayList<>();
        this.title = title;
        this.userID = user != null ? this.user.getId() : 0;
        this.favoritefilmsID = new ArrayList<>();
    }

    /**
     * Повертає назву бібліотеки фільмів.
     *
     * @return Назва бібліотеки фільмів.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Повертає ідентифікатор користувача, якому належить бібліотека фільмів.
     *
     * @return Ідентифікатор користувача.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Повертає список ідентифікаторів улюблених фільмів у бібліотеці.
     *
     * @return Список ідентифікаторів улюблених фільмів.
     */
    public List<Integer> getFavoritefilmsID() {
        return favoritefilmsID;
    }

    /**
     * Повертає користувача, якому належить бібліотека фільмів.
     *
     * @return Користувач.
     */
    @JsonIgnore
    public User getUser() {
        return user;
    }

    /**
     * Змінює назву бібліотеки фільмів.
     *
     * @param newTitle Нова назва бібліотеки фільмів.
     */
    public void changeLibraryTitle(String newTitle) {
        title = newTitle;
    }

    /**
     * Повертає список улюблених фільмів у бібліотеці.
     *
     * @return Список улюблених фільмів.
     */
    @JsonIgnore
    public List<Movie> getFavoritefilms() {
        return favoritefilms;
    }

    /**
     * Додає фільм до улюблених у бібліотеці.
     *
     * @param movie Фільм, який додається до улюблених.
     */
    public void addMovieToLibrary(Movie movie) {
        favoritefilms.add(movie);
    }

    /**
     * Перевизначений метод toString() для отримання рядкового представлення об'єкта.
     *
     * @return Рядкове представлення об'єкта MovieLibrary.
     */
    @Override
    public String toString() {

        return "\nБібліотека фільмів:" +
            "\nID: " + super.getId() +
            "\nУлюблені фільми: " + favoritefilmsID +
            "\nНазва: '" + title + '\'' +
            "\nНалежить користувачу: " + (user != null ? user.getUsername() : "") +
            "\n";
    }
}
