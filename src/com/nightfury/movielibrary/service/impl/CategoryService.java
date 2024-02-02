package com.nightfury.movielibrary.service.impl;

import com.nightfury.movielibrary.model.impl.Category;
import com.nightfury.movielibrary.service.Service;
import com.nightfury.movielibrary.service.jsonhandler.JsonDataReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реалізація сервісу для управління категоріями.
 */
public class CategoryService implements Service<Category> {

    /**
     * Список категорій.
     */
    private final List<Category> categories;

    /**
     * Конструктор класу CategoryService.
     */
    public CategoryService() {
        this.categories = loadAllCategories() != null ? loadAllCategories() : new ArrayList<>();
    }

    /**
     * Завантажує усі категорії з JSON файлу.
     *
     * @return Список категорій.
     */
    private List<Category> loadAllCategories() {
        return JsonDataReader.readMoviesInfoFile("Category.JSON", Category[].class);
    }

    /**
     * Повертає колекцію категорій.
     *
     * @return Колекція категорій.
     */
    public List<Category> getCategoriesCollection() {
        return this.categories;
    }

    /**
     * Знаходить категорію за ідентифікатором.
     *
     * @param id Ідентифікатор категорії.
     * @return Категорія або null, якщо категорію не знайдено.
     */
    @Override
    public Category findById(int id) {
        return categories.stream()
            .filter(category -> category.getId() == id)
            .findFirst()
            .orElse(null);
    }

    /**
     * Знаходить категорії за назвою.
     *
     * @param name Назва категорії.
     * @return Список категорій з вказаною назвою.
     */
    @Override
    public List<Category> findByName(String name) {
        return categories.stream()
            .filter(category -> category.getName().equals(name))
            .collect(Collectors.toList());
    }

    /**
     * Видаляє категорію за ідентифікатором.
     *
     * @param id Ідентифікатор категорії.
     */
    @Override
    public void delete(int id) {
        categories.removeIf(category -> category.getId() == id);
    }

    /**
     * Повертає усі категорії.
     *
     * @return Список усіх категорій.
     */
    @Override
    public List<Category> getAll() {
        return new ArrayList<>(this.categories);
    }
}
