package com.nightfury.movielibrary.model;

/**
 * Перелік шаблонів помилок, що використовуються в системі.
 */
public enum ErrorTemplates {

    /**
     * Поле %s є обов'язковим до заповнення.
     */
    REQUIRED("Поле %s є обов'язковим до заповнення."),

    /**
     * Поле %s не може бути меншим за %d симв.
     */
    MIN_LENGTH("Поле %s не може бути меншим за %d симв."),

    /**
     * Поле %s не може бути більшим за %d симв.
     */
    MAX_LENGTH("Поле %s не може бути більшим за %d симв."),

    /**
     * Поле %s лише латинські символи та символ _.
     */
    ONLY_LATIN("Поле %s лише латинські символи та символ _."),

    /**
     * Поле %s латинські символи, хочаб одна буква з великої, одна з малої та хочаб одна цифра.
     */
    PASSWORD(
        "Поле %s латинські символи, хочаб одна буква з великої, одна з малої та хочаб одна цифра."),

    /**
     * Поле %s має містити @ та одну крапку.
     */
    EMAIL_CONTAINS("Поле %s має містити @ та одну крапку."),

    /**
     * Поле %s не відповідає стандартному формату електронної пошти.
     */
    EMAIL_MATCHES("Поле %s не відповідає стандартному формату електронної пошти.");

    /**
     * Шаблон помилки.
     */
    private String template;

    /**
     * Конструктор класу ErrorTemplates.
     *
     * @param template Шаблон помилки.
     */
    ErrorTemplates(String template) {
        this.template = template;
    }

    /**
     * Повертає шаблон помилки.
     *
     * @return Шаблон помилки.
     */
    public String getTemplate() {
        return template;
    }
}
