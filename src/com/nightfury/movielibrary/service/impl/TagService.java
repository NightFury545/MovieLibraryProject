package com.nightfury.movielibrary.service.impl;

import com.nightfury.movielibrary.model.impl.Tag;
import com.nightfury.movielibrary.service.Service;
import com.nightfury.movielibrary.service.jsonhandler.JsonDataReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реалізація сервісу для управління тегами.
 */
public class TagService implements Service<Tag> {

    /**
     * Список тегів.
     */
    private final List<Tag> tags;

    /**
     * Конструктор класу TagService.
     */
    public TagService() {
        this.tags = loadAllTags() != null ? loadAllTags() : new ArrayList<>();
    }

    /**
     * Завантажує усі теги з JSON файлу.
     *
     * @return Список тегів.
     */
    private List<Tag> loadAllTags() {
        return JsonDataReader.readMoviesInfoFile("Tag.JSON", Tag[].class);
    }

    /**
     * Повертає колекцію тегів.
     *
     * @return Колекція тегів.
     */
    public List<Tag> getTagsCollection() {
        return this.tags;
    }

    /**
     * Знаходить тег за ідентифікатором.
     *
     * @param id Ідентифікатор тега.
     * @return Тег або null, якщо тег не знайдено.
     */
    @Override
    public Tag findById(int id) {
        return tags.stream()
            .filter(tag -> tag.getId() == id)
            .findFirst()
            .orElse(null);
    }

    /**
     * Знаходить теги за назвою.
     *
     * @param name Примітка тега.
     * @return Список тегів з вказаною приміткою.
     */
    @Override
    public List<Tag> findByName(String name) {
        return tags.stream()
            .filter(tag -> tag.getNote().equals(name))
            .collect(Collectors.toList());
    }

    /**
     * Видаляє тег за ідентифікатором.
     *
     * @param id Ідентифікатор тега.
     */
    @Override
    public void delete(int id) {
        tags.removeIf(tag -> tag.getId() == id);
    }

    /**
     * Повертає усі теги.
     *
     * @return Список усіх тегів.
     */
    @Override
    public List<Tag> getAll() {
        return new ArrayList<>(this.tags);
    }
}
