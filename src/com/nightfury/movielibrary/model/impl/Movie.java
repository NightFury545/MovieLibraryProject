package com.nightfury.movielibrary.model.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nightfury.movielibrary.model.Entity;
import java.util.ArrayList;
import java.util.List;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

/**
 * Клас, що представляє фільм у системі кінотеатру.
 */
public class Movie extends Entity {

    private final String name;
    private final int releaseYear;
    private final List<Category> categories;
    private final int[] categoriesID;
    private final List<Director> directors;
    private final int[] directorsID;
    private final List<Actor> actors;
    private final int[] actorsID;
    private final List<Tag> tags;
    private final int[] tagsID;
    private double rating;
    private String description;

    /**
     * Конструктор класу Movie.
     *
     * @param ID           Ідентифікатор фільму.
     * @param name         Назва фільму.
     * @param releaseYear  Рік випуску фільму.
     * @param rating       Рейтинг фільму.
     * @param description  Опис фільму.
     * @param categoriesID Масив ідентифікаторів категорій фільму.
     * @param categories   Список категорій фільму.
     * @param directorsID  Масив ідентифікаторів режисерів фільму.
     * @param directors    Список режисерів фільму.
     * @param actorsID     Масив ідентифікаторів акторів фільму.
     * @param actors       Список акторів фільму.
     * @param tagsID       Масив ідентифікаторів тегів фільму.
     * @param tags         Список тегів фільму.
     */
    public Movie(@JsonProperty("ID") int ID,
        @JsonProperty("name") String name,
        @JsonProperty("releaseYear") int releaseYear,
        @JsonProperty("rating") double rating,
        @JsonProperty("description") String description,
        @JsonProperty("categoriesID") int[] categoriesID,
        @JsonProperty("categories") List<Category> categories,
        @JsonProperty("directorsID") int[] directorsID,
        @JsonProperty("directors") List<Director> directors,
        @JsonProperty("actorsID") int[] actorsID,
        @JsonProperty("actors") List<Actor> actors,
        @JsonProperty("tagsID") int[] tagsID,
        @JsonProperty("tags") List<Tag> tags) {
        super(ID);
        this.name = name;
        this.releaseYear = releaseYear;
        this.categories = categories != null ? categories : new ArrayList<>();
        this.categoriesID = categoriesID != null ? categoriesID : new int[10];
        this.directors = directors != null ? directors : new ArrayList<>();
        this.directorsID = directorsID != null ? directorsID : new int[10];
        this.actors = actors != null ? actors : new ArrayList<>();
        this.actorsID = actorsID != null ? actorsID : new int[10];
        this.tags = tags != null ? tags : new ArrayList<>();
        this.tagsID = tagsID != null ? tagsID : new int[10];
        this.rating = rating;
        this.description = description;
    }

    /**
     * Повертає масив ідентифікаторів категорій фільму.
     *
     * @return Масив ідентифікаторів категорій.
     */
    public int[] getCategoriesID() {
        return categoriesID;
    }

    /**
     * Повертає масив ідентифікаторів режисерів фільму.
     *
     * @return Масив ідентифікаторів режисерів.
     */
    public int[] getDirectorsID() {
        return directorsID;
    }

    /**
     * Повертає масив ідентифікаторів акторів фільму.
     *
     * @return Масив ідентифікаторів акторів.
     */
    public int[] getActorsID() {
        return actorsID;
    }

    /**
     * Повертає масив ідентифікаторів тегів фільму.
     *
     * @return Масив ідентифікаторів тегів.
     */
    public int[] getTagsID() {
        return tagsID;
    }

    /**
     * Повертає назву фільму.
     *
     * @return Назва фільму.
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає рік випуску фільму.
     *
     * @return Рік випуску фільму.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Повертає список категорій фільму.
     *
     * @return Список категорій фільму.
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Повертає список режисерів фільму.
     *
     * @return Список режисерів фільму.
     */
    public List<Director> getDirectors() {
        return directors;
    }

    /**
     * Повертає список акторів фільму.
     *
     * @return Список акторів фільму.
     */
    public List<Actor> getActors() {
        return actors;
    }

    /**
     * Повертає список тегів фільму.
     *
     * @return Список тегів фільму.
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Повертає рейтинг фільму.
     *
     * @return Рейтинг фільму.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Повертає опис фільму.
     *
     * @return Опис фільму.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Додає тег до фільму.
     *
     * @param tag Тег, який додається до фільму.
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    /**
     * Встановлює рейтинг фільму.
     *
     * @param rating Рейтинг фільму.
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Встановлює опис фільму.
     *
     * @param description Опис фільму.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Перевизначений метод toString() для отримання рядкового представлення об'єкта.
     *
     * @return Рядкове представлення об'єкта Movie.
     */
    @Override
    public String toString() {
        AnsiConsole.systemInstall();

        StringBuilder sb = new StringBuilder();
        sb.append(Ansi.ansi().bgCyan().fgBrightBlack().a("☆Фільм:").reset())
            .append(Ansi.ansi().bgDefault().a(" ").reset())
            .append("\n");
        sb.append(Ansi.ansi().bgCyan().fgBrightBlack().a("•ID: '").reset())
            .append(Ansi.ansi().fgBrightCyan().a(super.getId()).reset()).append("\n");
        sb.append(Ansi.ansi().bgCyan().fgBrightBlack().a("•Назва: '").reset())
            .append(Ansi.ansi().fgBrightCyan().a(name).reset()).append("\n");
        sb.append(Ansi.ansi().bgCyan().fgBrightBlack().a("•Рік випуску: ").reset())
            .append(Ansi.ansi().fgBrightCyan().a(releaseYear).reset()).append("\n");
        sb.append(Ansi.ansi().bgCyan().fgBrightBlack().a("•Категорії: ")
            .reset());
        if (!categories.isEmpty()) {
            for (int i = 0; i < categories.size(); i++) {
                sb.append(Ansi.ansi().fgBrightCyan().a(categories.get(i).getName()).reset())
                    .append("\n");
            }
        } else {
            sb.append("\n");
        }
        sb.append(Ansi.ansi().bgCyan().fgBrightBlack().a("•Режисери: ")
            .reset());
        if (!directors.isEmpty()) {
            for (int i = 0; i < directors.size(); i++) {
                sb.append("\n")
                    .append(Ansi.ansi().fgBrightCyan().a(directors.get(i).getFirstname()).reset())
                    .append(" ")
                    .append(Ansi.ansi().fgBrightCyan().a(directors.get(i).getLastname()).reset())
                    .append("\n");
            }
        } else {
            sb.append("\n");
        }
        sb.append(Ansi.ansi().bgCyan().fgBrightBlack().a("•Актори: ")
            .reset());
        for (Actor actor : actors) {
            sb.append("\n").append(Ansi.ansi().fgBrightCyan().a(actor.getFirstname()).reset())
                .append(" ").append(Ansi.ansi().fgBrightCyan().a(actor.getLastname()).reset());
        }
        sb.append("\n").append(Ansi.ansi().bgCyan().bgCyan().fgBrightBlack().a("•Теги: ")
            .reset());
        for (Tag tag : tags) {
            sb.append("\n").append(Ansi.ansi().fgBrightCyan().a(tag.toString()).reset());
        }
        sb.append(Ansi.ansi().bgCyan().fgBrightBlack().a("•Рейтинг: ").reset())
            .append(Ansi.ansi().fgBrightCyan().a(rating).reset())
            .append("\n");
        sb.append(Ansi.ansi().bgCyan().fgBrightBlack().a("•Опис: '").reset())
            .append(Ansi.ansi().fgBrightCyan().a(description).reset()).append("\n");
        sb.append("----------------------------\n");

        AnsiConsole.systemUninstall();
        return sb.toString();
    }
}
