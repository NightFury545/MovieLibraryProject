package com.nightfury.movielibrary.exception;

/**
 * Виняток, що виникає, коли запитана сутність не знайдена.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Конструктор, який створює об'єкт винятку зі специфічним повідомленням про помилку.
     *
     * @param message Повідомлення про помилку, що пояснює причину винятку.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
