package com.nightfury.movielibrary.view.controller;

import com.nightfury.movielibrary.exception.EntityArgumentException;
import com.nightfury.movielibrary.exception.NotFoundException;
import com.nightfury.movielibrary.model.impl.Movie;
import com.nightfury.movielibrary.model.impl.MovieLibrary;
import com.nightfury.movielibrary.model.impl.User;
import com.nightfury.movielibrary.model.impl.User.Role;
import com.nightfury.movielibrary.service.impl.MovieLibraryService;
import com.nightfury.movielibrary.service.impl.UserService;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
 * Клас UserController відповідає за управління користувачами та їхніми діями. Включає методи для
 * створення облікового запису, входу в систему, відображення та видалення облікових записів, пошуку
 * користувачів за різними параметрами, створення та управління бібліотеками фільмів.
 */
public class UserController {

    // Оголошення сервісів для користувачів та бібліотек фільмів
    private final UserService userService;
    private final MovieLibraryService movieLibraryService;
    private final MovieLibraryController movieLibraryController;

    /**
     * Конструктор, який ініціалізує всі сервіси.
     */
    public UserController() {
        this.userService = new UserService();
        this.movieLibraryService = new MovieLibraryService();
        this.movieLibraryController = new MovieLibraryController();
    }

    /**
     * Створює новий обліковий запис користувача.
     *
     * @param password Пароль користувача.
     * @param birthday Дата народження користувача.
     * @param email    Електронна пошта користувача.
     * @param username Ім'я користувача.
     * @param role     Роль користувача.
     * @return Створений обліковий запис користувача або null, якщо обліковий запис не був
     * створений.
     */
    public User createAccount(String password, LocalDate birthday, String email, String username,
        Role role) {

        for (User user : userService.getUsersCollection()) {
            if (user.getUsername().equals(username)) {
                System.err.println("Користувач з таким ім'ям вже існує!");
                return null;
            }
        }

        try {
            User user = new User(new Random().nextInt(1, 999), password, birthday, email, username,
                role);
            userService.addUser(user);
            System.out.println("Обліковий запис успішно створено!");
            userService.saveUsersToFile();
            return user;
        } catch (EntityArgumentException e) {
            System.err.println("Помилка під час реєстрації користувача" + e.getErrors());
            return null;
        }
    }

    /**
     * Виконує вхід в систему за допомогою облікових даних користувача.
     *
     * @param username Ім'я користувача.
     * @param password Пароль користувача.
     * @param email    Електронна пошта користувача.
     * @return Об'єкт користувача, якщо вхід в систему виконано успішно, або null у протилежному
     * випадку.
     */
    public User signIn(String username, String password, String email) {
        for (User user : userService.getUsersCollection()) {
            if (user.getUsername().equals(username)) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    System.out.println("Ви успішно увійшли в обліковий запис!");
                    return user;
                } else {
                    System.err.println("Неправильний пароль або електронна адреса!");
                    return null;
                }
            }
        }

        System.err.println("Обліковий запис з таким іменем не знайдено!");
        return null;
    }

    /**
     * Відображає усі облікові записи користувачів.
     */
    public void showAllAccounts() {
        userService.getAll().forEach(System.out::println);
    }

    /**
     * Відображає інформацію про обліковий запис користувача.
     *
     * @param user Об'єкт користувача.
     */
    public void showInfoAboutAccount(User user) {
        System.out.println(user.toString());
    }

    /**
     * Видаляє обліковий запис користувача за його ідентифікатором.
     *
     * @param ID Ідентифікатор користувача.
     */
    public void deleteUser(int ID) {
        try {
            userService.delete(ID);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Обліковий запис успішно видалено!");
    }

    /**
     * Створює бібліотеку фільмів для користувача.
     *
     * @param user  Об'єкт користувача.
     * @param title Назва бібліотеки фільмів.
     */
    public void createMovieLibrary(User user, String title) {
        try {
            userService.createMovieLibrary(user, title);
            System.out.println("Бібліотека фільмів для користувача " + user.toString() + "\n"
                + "успішно створена!");
            movieLibraryService.addMovieLibrary(user.getMovielibrary());
            movieLibraryService.saveMovieLibrariesToFile();
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Додає фільм до бібліотеки фільмів користувача за його ідентифікатором.
     *
     * @param user    Об'єкт користувача.
     * @param movieID Ідентифікатор фільму.
     */
    public void addMovieToLibrary(User user, int movieID) {
        List<Movie> movies = movieLibraryController.getMovieList();
        Movie movie = movies.stream()
            .filter(film -> film.getId() == movieID)
            .findFirst()
            .orElse(null);

        if (movie != null) {
            if (user.getMovielibrary() != null) {
                user.getMovielibrary().addMovieToLibrary(movie);
                user.getMovielibrary().getFavoritefilmsID().add(movieID);
                movieLibraryService.saveMovieLibrariesToFile();
                System.out.println(
                    "Фільм '" + movie.getName() + "' успішно додано до вашої бібліотеки.");
            } else {
                System.err.println("Спочатку потрібно створити бібліотеку фільмів!");
            }
        } else {
            System.err.println("Фільм з ID " + movieID + " не знайдено.");
        }

    }

    /**
     * Відображає всі фільми у бібліотеці фільмів користувача.
     *
     * @param user Об'єкт користувача.
     */
    public void showMoviesInLibrary(User user) {
        if (user.getMovielibrary() != null) {
            MovieLibrary movieLibrary = user.getMovielibrary();
            List<Integer> moviesID = movieLibrary.getFavoritefilmsID();
            List<Movie> movies = movieLibraryController.getMovieList();
            System.out.println("Фільми у вашій бібліотеці:");
            for (Integer movieID : moviesID) {
                for (Movie movie : movies) {
                    if (movie.getId() == movieID) {
                        System.out.println(movie);
                        break;
                    }
                }
            }
        } else {
            System.err.println("Спочатку створіть бібліотеку фільмів!");
        }
    }

    /**
     * Конфігурує бібліотеки фільмів для користувача.
     *
     * @param user Об'єкт користувача.
     */
    public void configureMovieLibraries(User user) {
        List<MovieLibrary> movieLibraries = movieLibraryService.getMovieLibrariesCollection();
        for (MovieLibrary movieLibrary : movieLibraries) {
            if (movieLibrary.getUserID() == user.getId()) {
                user.setMovielibrary(movieLibrary);
            }
        }
    }

    /**
     * Відображає усі бібліотеки фільмів.
     */
    public void showAllMovieLibraries() {
        List<MovieLibrary> movieLibraries = movieLibraryService.getMovieLibrariesCollection();
        movieLibraries.forEach(System.out::println);
    }
}
