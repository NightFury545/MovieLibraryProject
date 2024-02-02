package com.nightfury.movielibrary.exception;

/**
 * Виняток, що виникає при зчитуванні даних з JSON файлу.
 */
public class JsonDataReaderException extends RuntimeException {

    /**
     * Конструктор, який створює об'єкт винятку зі специфічним повідомленням про помилку.
     *
     * @param message Повідомлення про помилку, що пояснює причину винятку.
     */
    public JsonDataReaderException(String message) {
        super(message);
    }
}
