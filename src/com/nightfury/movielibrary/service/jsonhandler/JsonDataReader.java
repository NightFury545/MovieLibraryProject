package com.nightfury.movielibrary.service.jsonhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nightfury.movielibrary.exception.JsonDataReaderException;
import com.nightfury.movielibrary.model.Entity;
import com.nightfury.movielibrary.model.impl.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.io.File;

/**
 * Клас, який надає методи для читання даних з JSON файлів.
 */
public final class JsonDataReader {

    /**
     * Читає вміст JSON файлу з фільмами та повертає список екземплярів відповідних сутностей.
     *
     * @param filename Назва файлу.
     * @param clazz    Клас, до якого буде проведено приведення типу.
     * @param <T>      Тип сутності.
     * @return Список екземплярів відповідних сутностей.
     * @throws JsonDataReaderException Виняток, який виникає при неможливості прочитати JSON дані з
     *                                 файлу.
     */
    public static <T extends Entity> List<T> readMoviesInfoFile(String filename, Class<T[]> clazz)
        throws JsonDataReaderException {
        String pathToFile = JsonPaths.PATH_TO_MOVIES_DIR + filename;

        File file = new File(pathToFile);
        if (!file.exists() || file.length() == 0) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            T[] entities = objectMapper.readValue(new File(pathToFile), clazz);
            return new ArrayList<>(Arrays.asList(entities));
        } catch (IOException e) {
            throw new JsonDataReaderException(("Помилка під час зчитування файлу %s. "
                + "Можливо файл був переміщений або видалений.").formatted(filename));
        }

    }

    /**
     * Читає вміст JSON файлу з користувачами та повертає список користувачів.
     *
     * @param filename Назва файлу.
     * @return Список користувачів.
     * @throws JsonDataReaderException Виняток, який виникає при неможливості прочитати JSON дані з
     *                                 файлу.
     */
    public static List<User> readUsersDataFile(String filename) throws JsonDataReaderException {
        String pathToFile = JsonPaths.PATH_TO_USERS_DIR + filename;

        File file = new File(pathToFile);
        if (!file.exists() || file.length() == 0) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            User[] users = objectMapper.readValue(new File(pathToFile), User[].class);
            return new ArrayList<>(Arrays.asList(users));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new JsonDataReaderException(("Помилка під час зчитування файлу %s. "
                + "Можливо файл був переміщений або видалений.").formatted(filename));
        }
    }
}
