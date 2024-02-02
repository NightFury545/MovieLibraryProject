package com.nightfury.movielibrary.service.impl;

import com.nightfury.movielibrary.exception.NotFoundException;
import com.nightfury.movielibrary.model.impl.User;
import com.nightfury.movielibrary.service.Service;
import com.nightfury.movielibrary.service.jsonhandler.JsonDataReader;
import com.nightfury.movielibrary.service.jsonhandler.JsonDataWriter;
import com.nightfury.movielibrary.service.jsonhandler.JsonPaths;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Реалізація сервісу для управління користувачами.
 */
public class UserService implements Service<User> {

    /**
     * Список користувачів.
     */
    private List<User> users;

    /**
     * Конструктор класу UserService.
     */
    public UserService() {
        this.users = loadAllUsers() != null ? loadAllUsers() : new ArrayList<>();
    }

    /**
     * Завантажує усіх користувачів з JSON файлу.
     *
     * @return Список користувачів.
     */
    private List<User> loadAllUsers() {
        return JsonDataReader.readUsersDataFile("User.JSON");
    }

    /**
     * Повертає колекцію користувачів.
     *
     * @return Колекція користувачів.
     */
    public List<User> getUsersCollection() {
        return this.users;
    }

    /**
     * Додає користувача до списку.
     *
     * @param user Користувач, який буде доданий.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Зберігає користувачів у файл.
     */
    public void saveUsersToFile() {
        JsonDataWriter.writeUserToFile("User.JSON", this.users);
    }

    /**
     * Створює бібліотеку фільмів для користувача.
     *
     * @param user  Користувач, для якого створюється бібліотека фільмів.
     * @param title Назва бібліотеки фільмів.
     */
    public void createMovieLibrary(User user, String title) {
        for (User currentUser : users) {
            if (currentUser.getId() == user.getId()) {
                currentUser.createMovieLibrary(new Random().nextInt(0, 999), title);
                return;
            }
        }
        throw new NotFoundException("Такого користувача не існує");
    }

    /**
     * Оновлює ім'я користувача.
     *
     * @param user        Користувач, ім'я якого оновлюється.
     * @param newUsername Нове ім'я користувача.
     */
    public void updateUsername(User user, String newUsername) {
        user.setUsername(newUsername);
    }

    /**
     * Знаходить користувача за ідентифікатором.
     *
     * @param id Ідентифікатор користувача.
     * @return Користувач або null, якщо користувач не знайдений.
     */
    @Override
    public User findById(int id) {
        if (users.isEmpty()) {
            throw new NotFoundException("Список користувачів порожній!");
        }
        try {
            return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Обліковий запис з таким ID не знайдено!");
        }
    }

    /**
     * Знаходить користувача за електронною адресою.
     *
     * @param email Електронна адреса користувача.
     * @return Користувач або null, якщо користувач не знайдений.
     */
    public User findByEmail(String email) {
        if (users.isEmpty()) {
            throw new NotFoundException("Список користувачів порожній!");
        }

        try {
            return users.stream().filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Обліковий запис з такою електронною адресою не знайдено!");
        }
    }

    /**
     * Знаходить користувачів за ім'ям.
     *
     * @param username Ім'я користувача.
     * @return Список користувачів з вказаним ім'ям.
     */
    @Override
    public List<User> findByName(String username) {
        if (users.isEmpty()) {
            throw new NotFoundException("Список користувачів порожній!");
        }
        try {
            return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Обліковий запис з таким іменем не знайдено!");
        }
    }

    /**
     * Видаляє користувача за ідентифікатором.
     *
     * @param id Ідентифікатор користувача.
     */
    @Override
    public void delete(int id) {
        if (users.isEmpty()) {
            throw new NotFoundException("Список користувачів порожній!");
        }
        try {
            users.removeIf(user -> user.getId() == id);
            saveUsersToFile();
            if (this.users.isEmpty()) {
                try {
                    FileWriter writer = new FileWriter(JsonPaths.PATH_TO_USERS_DIR + "User.JSON");
                    writer.write("");
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Файлу не існує!");
                }
            }
        } catch (NullPointerException e) {
            throw new NotFoundException("Облікового запису з таким ID не існує!");
        }
    }

    /**
     * Видаляє користувача за електронною адресою.
     *
     * @param email Електронна адреса користувача.
     */
    public void deleteByEmail(String email) {
        if (users.isEmpty()) {
            throw new NotFoundException("Список користувачів порожній!");
        }
        try {
            users.removeIf(user -> user.getEmail().equals(email));
        } catch (NullPointerException e) {
            throw new NotFoundException("Облікового запису з такою електронною адресою не існує!");
        }
    }

    /**
     * Повертає усіх користувачів.
     *
     * @return Список усіх користувачів.
     */
    @Override
    public List<User> getAll() {
        return new ArrayList<>(this.users);
    }
}
