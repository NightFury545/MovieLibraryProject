package com.nightfury.movielibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Абстрактний клас, що представляє сутність системи.
 */
public abstract class Entity {

    /**
     * Унікальний ідентифікатор сутності.
     */
    protected final int ID;

    /**
     * Список помилок, що виникають при взаємодії з сутністю.
     */
    @JsonIgnore
    protected List<String> errors;

    /**
     * Конструктор класу Entity.
     *
     * @param ID Унікальний ідентифікатор сутності.
     */
    protected Entity(int ID) {
        errors = new ArrayList<>();
        this.ID = ID;
    }

    /**
     * Повертає унікальний ідентифікатор сутності.
     *
     * @return Унікальний ідентифікатор сутності.
     */
    public int getId() {
        return ID;
    }

    /**
     * Повертає список помилок, що виникають при взаємодії з сутністю.
     *
     * @return Список помилок.
     */
    @JsonIgnore
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Перевіряє, чи є поточна сутність рівною іншій сутності.
     *
     * @param o Об'єкт для порівняння.
     * @return true, якщо сутності рівні, в іншому випадку - false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(ID, entity.ID);
    }

    /**
     * Обчислює хеш-код поточної сутності.
     *
     * @return Хеш-код сутності.
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
