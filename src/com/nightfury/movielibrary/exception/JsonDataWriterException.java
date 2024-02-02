package com.nightfury.movielibrary.exception;

/**
 * Виняток, що виникає при записі даних у JSON файл.
 */
public class JsonDataWriterException extends RuntimeException {

    /**
     * Конструктор, який створює об'єкт винятку зі специфічним повідомленням про помилку.
     *
     * @param message Повідомлення про помилку, що пояснює причину винятку.
     */
    public JsonDataWriterException(String message) {
        super(message);
    }
}
