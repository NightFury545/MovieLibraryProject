package com.nightfury.movielibrary.model.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nightfury.movielibrary.model.Entity;

/**
 * Клас, що представляє тег для фільму.
 */
public class Tag extends Entity {

    private final String note;

    /**
     * Конструктор класу Tag.
     *
     * @param ID   Ідентифікатор тегу.
     * @param note Примітка до тегу.
     */
    public Tag(@JsonProperty("ID") int ID, @JsonProperty("note") String note) {
        super(ID);
        this.note = note;
    }

    /**
     * Повертає примітку до тегу.
     *
     * @return Примітка до тегу.
     */
    public String getNote() {
        return note;
    }

    /**
     * Перевизначений метод toString() для отримання рядкового представлення об'єкта.
     *
     * @return Рядкове представлення об'єкта Tag.
     */
    @Override
    public String toString() {
        return "Тег:" +
            "\nID:" + super.getId() + '\'' +
            "\nПримітка:" + note + '\'' +
            '\n';
    }
}
