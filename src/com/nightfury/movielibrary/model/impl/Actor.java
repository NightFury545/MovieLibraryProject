package com.nightfury.movielibrary.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nightfury.movielibrary.model.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, який представляє актора у системі кінотеатру.
 */
public class Actor extends Entity {

    private String firstname;
    private String lastname;
    private final List<Movie> filmography;
    private final int[] filmographyID;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate birthday;
    private String country;

    /**
     * Конструктор класу Actor.
     *
     * @param ID            Ідентифікатор актора.
     * @param firstname     Ім'я актора.
     * @param lastname      Прізвище актора.
     * @param filmography   Фільмографія актора.
     * @param filmographyID Масив ідентифікаторів фільмів у фільмографії актора.
     * @param birthday      Дата народження актора.
     * @param country       Країна походження актора.
     */
    public Actor(@JsonProperty("ID") int ID,
        @JsonProperty("firstname") String firstname,
        @JsonProperty("lastname") String lastname,
        @JsonProperty("filmography") List<Movie> filmography,
        @JsonProperty("filmographyID") int[] filmographyID,
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
     * Повертає масив ідентифікаторів фільмів у фільмографії актора.
     *
     * @return Масив ідентифікаторів фільмів.
     */
    public int[] getFilmographyID() {
        return filmographyID;
    }

    /**
     * Повертає ім'я актора.
     *
     * @return Ім'я актора.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Повертає прізвище актора.
     *
     * @return Прізвище актора.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Повертає фільмографію актора.
     *
     * @return Список фільмів у фільмографії актора.
     */
    public List<Movie> getFilmography() {
        return filmography;
    }

    /**
     * Повертає дату народження актора.
     *
     * @return Дата народження актора.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Повертає країну походження актора.
     *
     * @return Країна походження актора.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Перевизначений метод toString() для отримання рядкового представлення об'єкта.
     *
     * @return Рядкове представлення об'єкта Actor.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nАктор:");
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
