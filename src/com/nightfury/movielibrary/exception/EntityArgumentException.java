package com.nightfury.movielibrary.exception;

import java.util.List;

/**
 * Виняток, який виникає, коли аргументи сутності недійсні.
 */
public class EntityArgumentException extends IllegalArgumentException {

    private final List<String> errors;

    /**
     * Конструктор, що створює об'єкт винятку зі списком помилок.
     *
     * @param errors Список рядків, що представляють помилки, пов'язані з недійсними аргументами
     *               сутності.
     */
    public EntityArgumentException(List<String> errors) {
        this.errors = errors;
    }

    /**
     * Повертає список помилок, пов'язаних з недійсними аргументами сутності.
     *
     * @return Список рядків, що представляють помилки.
     */
    public List<String> getErrors() {
        return errors;
    }
}
