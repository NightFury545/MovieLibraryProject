package com.nightfury.movielibrary.exception;

/**
 * Виняток, що виникає при помилці входу користувача в систему.
 */
public class SignInException extends RuntimeException {

    /**
     * Конструктор, який створює об'єкт винятку зі специфічним повідомленням про помилку.
     *
     * @param message Повідомлення про помилку, що пояснює причину винятку.
     */
    public SignInException(String message) {
        super(message);
    }
}
