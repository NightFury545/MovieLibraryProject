package com.nightfury.movielibrary;

import com.nightfury.movielibrary.view.Menu;

/**
 * Головний клас додатка, який містить метод {@code main} для запуску програми.
 */
public class Application {

    /**
     * Метод, який запускає програму. Викликає метод {@code runner()} класу {@code Menu}.
     *
     * @param args аргументи командного рядка (не використовуються в цьому додатку).
     */
    public static void main(String[] args) {
        Menu.runner();
    }
}