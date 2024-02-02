package com.nightfury.movielibrary.exception;

/**
 * Виняток, що виникає при помилці реєстрації користувача.
 */
public class SignUpException extends RuntimeException {

    /**
     * Конструктор, який створює об'єкт винятку зі специфічним повідомленням про помилку.
     *
     * @param message Повідомлення про помилку, що пояснює причину винятку.
     */
    public SignUpException(String message) {
        super(message);
    }
}
