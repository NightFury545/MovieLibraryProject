package com.nightfury.movielibrary.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nightfury.movielibrary.model.Entity;
import com.nightfury.movielibrary.exception.EntityArgumentException;
import com.nightfury.movielibrary.model.ErrorTemplates;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Клас, що представляє користувача системи.
 */
public class User extends Entity {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private final String password;

    @JsonProperty("birthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate birthday;

    @JsonProperty("email")
    private final String email;
    private MovieLibrary movielibrary;
    private int movielibraryID;

    @JsonProperty("role")
    private final Role role;

    /**
     * Конструктор класу User.
     *
     * @param ID             Ідентифікатор користувача.
     * @param password       Пароль користувача.
     * @param birthday       Дата народження користувача.
     * @param email          Електронна пошта користувача.
     * @param username       Ім'я користувача.
     * @param role           Роль користувача.
     * @param movielibraryID Ідентифікатор бібліотеки фільмів користувача.
     */
    public User(@JsonProperty("id") int ID, @JsonProperty("password") String password,
        @JsonProperty("birthday") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
        @JsonProperty("email") String email, @JsonProperty("username") String username,
        @JsonProperty("role") Role role, @JsonProperty("movielibraryID") int movielibraryID) {
        super(ID);
        this.password = setPassword(password);
        this.birthday = birthday;
        this.email = setEmail(email);
        setUsername(username);
        this.role = role;
        this.movielibraryID = movielibraryID;
    }

    /**
     * Конструктор класу User.
     *
     * @param ID       Ідентифікатор користувача.
     * @param password Пароль користувача.
     * @param birthday Дата народження користувача.
     * @param email    Електронна пошта користувача.
     * @param username Ім'я користувача.
     * @param role     Роль користувача.
     */
    public User(int ID, String password, LocalDate birthday, String email, String username,
        Role role) {
        super(ID);
        this.password = setPassword(password);
        this.birthday = birthday;
        this.email = setEmail(email);
        setUsername(username);
        this.role = role;
    }

    /**
     * Повертає роль користувача.
     *
     * @return Роль користувача.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Повертає пароль користувача.
     *
     * @return Пароль користувача.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Повертає дату народження користувача.
     *
     * @return Дата народження користувача.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Повертає бібліотеку фільмів користувача.
     *
     * @return Бібліотека фільмів користувача.
     */
    public MovieLibrary getMovielibrary() {
        return movielibrary;
    }

    /**
     * Повертає ідентифікатор бібліотеки фільмів користувача.
     *
     * @return Ідентифікатор бібліотеки фільмів користувача.
     */
    @JsonProperty("movielibraryID")
    public int getMovieLibraryID() {
        if (movielibrary != null) {
            return movielibrary.getId();
        } else {
            return 0;
        }
    }

    /**
     * Повертає електронну пошту користувача.
     *
     * @return Електронна пошта користувача.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Повертає ім'я користувача.
     *
     * @return Ім'я користувача.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Встановлює бібліотеку фільмів користувача.
     *
     * @param movielibrary Бібліотека фільмів користувача.
     */
    public void setMovielibrary(MovieLibrary movielibrary) {
        this.movielibrary = movielibrary;
    }

    /**
     * Створює нову бібліотеку фільмів для користувача.
     *
     * @param ID    Ідентифікатор нової бібліотеки фільмів.
     * @param title Назва нової бібліотеки фільмів.
     */
    public void createMovieLibrary(int ID, String title) {
        if (this.movielibrary == null) {
            this.movielibrary = new MovieLibrary(ID, title, this);
        }
    }

    /**
     * Встановлює ім'я користувача та перевіряє його на валідність.
     *
     * @param username Ім'я користувача.
     */
    public void setUsername(String username) {
        final String templateName = "логіну";

        if (username.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (username.length() < 4) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 4));
        }
        if (username.length() > 24) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 24));
        }
        var pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        if (!pattern.matcher(username).matches()) {
            errors.add(ErrorTemplates.ONLY_LATIN.getTemplate().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.username = username;
    }

    /**
     * Встановлює електронну пошту користувача та перевіряє її на валідність.
     *
     * @param email Електронна пошта користувача.
     * @return Електронна пошта користувача.
     */
    private String setEmail(String email) {
        final String templateName = "електронної пошти";

        if (email.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (email.length() < 12) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 12));
        }
        if (email.length() > 28) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 28));
        }

        if (!(email.contains("@") && email.contains("."))) {
            errors.add(ErrorTemplates.EMAIL_CONTAINS.getTemplate().formatted(templateName));
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.add(ErrorTemplates.EMAIL_MATCHES.getTemplate().formatted(templateName));
        }

        if (!errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        return email;
    }

    /**
     * Встановлює пароль користувача та перевіряє його на валідність.
     *
     * @param password Пароль користувача.
     * @return Пароль користувача.
     */
    private String setPassword(String password) {
        final String templateName = "паролю";

        if (password.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (password.length() < 8) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 4));
        }
        if (password.length() > 128) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 128));
        }
        var pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$");
        if (!pattern.matcher(password).matches()) {
            errors.add(ErrorTemplates.PASSWORD.getTemplate().formatted(templateName, 24));
        }

        if (!errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        return password;
    }

    /**
     * Перевизначений метод equals() для порівняння об'єктів User за їх електронною поштою.
     *
     * @param o Об'єкт для порівняння.
     * @return true, якщо об'єкти рівні за електронною поштою, в іншому випадку - false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    /**
     * Перевизначений метод hashCode() для обчислення хеш-коду об'єкту User на основі його
     * електронної пошти.
     *
     * @return Хеш-код об'єкту User.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Перевизначений метод toString() для отримання рядкового представлення об'єкта User.
     *
     * @return Рядкове представлення об'єкта User.
     */
    @Override
    public String toString() {
        String libraryInfo =
            (movielibrary != null) ? movielibrary.toString() : "Бібліотека фільмів не доступна";
        return "Користувач:" + "\nID: '" + super.getId() + '\'' + "\nІм'я користувача: '" + username
            + '\'' + "\nПароль: " + password + "\nБібліотека фільмів: " + libraryInfo
            + "\nЕлектронна пошта: '" + email + '\'' + "\nДата народження: " + birthday + "\n";
    }

    /**
     * Перечислення, що представляє ролі користувачів.
     */
    public enum Role {
        ADMIN("Адмін"), USER("Користувач");

        private String role;

        Role(String role) {
            this.role = role;
        }

        /**
         * Повертає назву ролі.
         *
         * @return Назва ролі.
         */
        public String getRole() {
            return this.role;
        }
    }
}
