package com.nightfury.movielibrary.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nightfury.movielibrary.model.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє режисера у системі кінотеатру.
 */
public class Director extends Entity {

    private String firstname;
    private String lastname;
    private final List<Movie> filmography;
    private final int[] filmographyID;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate birthday;
    private String country;

    /**
     * Конструктор класу Director.
     *
     * @param ID            Ідентифікатор режисера.
     * @param firstname     Ім'я режисера.
     * @param lastname      Прізвище режисера.
     * @param filmographyID Масив ідентифікаторів фільмів у фільмографії режисера.
     * @param filmography   Фільмографія режисера.
     * @param birthday      Дата народження режисера.
     * @param country       Країна походження режисера.
     */
    public Director(@JsonProperty("ID") int ID,
        @JsonProperty("firstname") String firstname,
        @JsonProperty("lastname") String lastname,
        @JsonProperty("filmographyID") int[] filmographyID,
        @JsonProperty("filmography") List<Movie> filmography,
        @JsonProperty("birthday") LocalDate birthday,
        @JsonProperty("country") String country) {
        super(ID);
        this.firstname = firstname;
        this.lastname = lastname;
        this.filmography = filmography != null ? filmography : new ArrayList<>();
        this.filmographyID = filmographyID != null ? filmographyID : new int[10];
        this.birthday = birthday;
        this.country = country;
    }

    /**
     * Повертає масив ідентифікаторів фільмів у фільмографії режисера.
     *
     * @return Масив ідентифікаторів фільмів.
     */
    public int[] getFilmographyID() {
        return filmographyID;
    }

    /**
     * Повертає ім'я режисера.
     *
     * @return Ім'я режисера.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Повертає прізвище режисера.
     *
     * @return Прізвище режисера.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Повертає фільмографію режисера.
     *
     * @return Список фільмів у фільмографії режисера.
     */
    public List<Movie> getFilmography() {
        return filmography;
    }

    /**
     * Повертає дату народження режисера.
     *
     * @return Дата народження режисера.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Повертає країну походження режисера.
     *
     * @return Країна походження режисера.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Перевизначений метод toString() для отримання рядкового представлення об'єкта.
     *
     * @return Рядкове представлення об'єкта Director.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nРежисер:");
        sb.append("\nID: '").append(super.ID).append('\'');
        sb.append("\nІм'я: '").append(firstname).append('\'');
        sb.append("\nПрізвище: '").append(lastname).append('\'');
        sb.append("\nКількість фільмів у фільмографії: ").append(filmography.size());
        sb.append("\nДата народження: ").append(birthday);
        sb.append("\nКраїна: '").append(country).append('\'');
        sb.append("\n");
        return sb.toString();
    }
}
