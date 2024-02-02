package com.nightfury.movielibrary.service.jsonhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nightfury.movielibrary.exception.JsonDataWriterException;
import com.nightfury.movielibrary.model.impl.MovieLibrary;
import com.nightfury.movielibrary.model.impl.User;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Клас, який надає методи для запису даних у JSON файли.
 */
public final class JsonDataWriter {

    /**
     * Записує дані про користувачів у JSON файл.
     *
     * @param filename Назва файлу.
     * @param users    Список користувачів.
     * @throws JsonDataWriterException Виняток, який виникає при неможливості зберегти дані у JSON
     *                                 файл.
     */
    public static void writeUserToFile(String filename, List<User> users)
        throws JsonDataWriterException {
        String pathToFile = JsonPaths.PATH_TO_USERS_DIR + filename;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        try {
            objectWriter.writeValue(new File(pathToFile), users);
        } catch (IOException e) {
            throw new JsonDataWriterException(
                "Не вдалося зберегти користувача %s у файл.".formatted(users.toString()));
        }
    }

    /**
     * Записує дані про бібліотеки фільмів у JSON файл.
     *
     * @param filename     Назва файлу.
     * @param movieLibrary Список бібліотек фільмів.
     * @throws JsonDataWriterException Виняток, який виникає при неможливості зберегти дані у JSON
     *                                 файл.
     */
    public static void writeMovieLibraryToFile(String filename, List<MovieLibrary> movieLibrary)
        throws JsonDataWriterException {
        String pathToFile = JsonPaths.PATH_TO_MOVIES_DIR + filename;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        try {
            objectWriter.writeValue(new File(pathToFile), movieLibrary);
        } catch (IOException e) {
            throw new JsonDataWriterException(
                "Не вдалося зберегти бібліотеку фільмів %s у файл.".formatted(
                    movieLibrary.toString()));
        }
    }
}
