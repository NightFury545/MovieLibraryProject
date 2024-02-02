package com.nightfury.movielibrary.view;

import com.nightfury.movielibrary.view.controller.MovieLibraryController;
import com.nightfury.movielibrary.view.controller.UserController;
import com.nightfury.movielibrary.model.impl.User;
import com.nightfury.movielibrary.model.impl.User.Role;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import org.fusesource.jansi.Ansi;

/**
 * Клас Menu представляє консольне меню для управління бібліотекою фільмів. Містить методи для
 * реєстрації та авторизації користувачів, відображення меню відповідно до прав доступу користувача,
 * а також для керування різними функціональними можливостями програми.
 */
public class Menu {

    /**
     * Метод, який відповідає за запуск головного меню програми.
     */
    static public void runner() {
        // Ініціалізація сканера для введення з консолі
        Scanner scanner = new Scanner(System.in);
        // Створення об'єктів контролерів користувачів та бібліотек фільмів
        UserController userController = new UserController();
        MovieLibraryController movieLibraryController = new MovieLibraryController();
        // Поточний користувач, який ввійшов в систему
        User currentUser = null;

        // Безкінечний цикл для взаємодії з користувачем
        while (true) {
            // Виведення головного меню
            System.out.println("\n☆☆☆Бібліотека фільмів☆☆☆\n");
            System.out.println("Меню:");
            System.out.println("1. Зареєструватися");
            System.out.println("2. Авторизуватися");

            int choice;

            // Вибір опції користувачем
            System.out.print("Виберіть опцію: ");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.print("Введіть цифру!\n");
                scanner.nextLine();
                continue;
            }

            // Обробка вибору користувача
            switch (choice) {
                case 1:
                    // Реєстрація нового користувача
                    System.out.println("Введіть ім'я: ");
                    String username = scanner.next();

                    System.out.println("Введіть пароль: ");
                    String password = scanner.next();

                    System.out.println("Введіть електронну пошту: ");
                    String email = scanner.next();

                    System.out.println("Введіть дату народження: ");
                    String birthday = scanner.next();

                    System.out.println("Оберіть роль: ");
                    System.out.println("1. Адмін");
                    System.out.println("2. Користувач");
                    int role = scanner.nextInt();
                    try {
                        // Створення облікового запису з введеними даними
                        if (role == 1) {
                            currentUser = userController.createAccount(password,
                                LocalDate.parse(birthday), email, username, Role.ADMIN);
                        } else if (role == 2) {
                            currentUser = userController.createAccount(password,
                                LocalDate.parse(birthday), email, username, Role.USER);
                        } else {
                            System.err.println("Ви обрали невірний пункт!");
                            break;
                        }

                        if (currentUser == null) {
                            break;
                        }
                    } catch (DateTimeParseException e) {
                        System.err.println(
                            "Неправильний формат дати. Будь ласка, введіть дату у форматі yyyy-MM-dd.");
                        break;
                    }
                    break;
                case 2:
                    // Авторизація користувача
                    System.out.println("Введіть ім'я: ");
                    String signInUsername = scanner.next();

                    System.out.println("Введіть пароль: ");
                    String signInPassword = scanner.next();

                    System.out.println("Введіть електронну пошту: ");
                    String signInEmail = scanner.next();

                    currentUser = userController.signIn(signInUsername, signInPassword,
                        signInEmail);
                    if (currentUser != null) {
                        userController.configureMovieLibraries(currentUser);
                    }
                    // Підменю для авторизованого користувача
                    while (true) {
                        if (currentUser != null) {
                            System.out.println("Меню:");
                            // Відображення доступних опцій відповідно до ролі користувача
                            System.out.println("1. Власна бібліотека фільмів");
                            System.out.println("2. Фільми");
                            System.out.println("3. Актори");
                            System.out.println("4. Режисери");
                            System.out.println("5. Категорії");
                            System.out.println(
                                "6. Переглянути інформацію про свій обліковий запис");
                            System.out.println("7. Вихід");
                            System.out.println(Ansi.ansi().fgRed()
                                .a("8. Перегляд інформації про всіх користувачів ☠").reset());
                            System.out.println(
                                Ansi.ansi().fgRed().a("9. Видалення користувачів ☠").reset());
                            try {
                                choice = scanner.nextInt();
                            } catch (InputMismatchException e) {
                                System.err.print("Введіть цифру!\n");
                                scanner.nextLine();
                                continue;
                            }

                            switch (choice) {
                                // Обробка вибору користувача у підменю
                                case 1:
                                    // Опції для роботи з бібліотекою фільмів
                                    System.out.println("1. Створити бібліотеку");
                                    System.out.println("2. Додати фільм до бібліотеки");
                                    System.out.println("3. Переглянути фільми в бібліотеці");
                                    int movieLibraryChoice;
                                    try {
                                        movieLibraryChoice = scanner.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.err.print("Введіть цифру!\n");
                                        scanner.nextLine();
                                        continue;
                                    }

                                    if (movieLibraryChoice == 1) {
                                        System.out.println("Введіть назву бібліотеки фільмів: ");
                                        String title = scanner.next();
                                        userController.createMovieLibrary(currentUser, title);
                                    } else if (movieLibraryChoice == 2) {
                                        System.out.println("Введіть айді фільму:");
                                        try {
                                            movieLibraryChoice = scanner.nextInt();
                                            userController.addMovieToLibrary(currentUser,
                                                movieLibraryChoice);
                                        } catch (InputMismatchException e) {
                                            System.err.print("Введіть цифру!\n");
                                            scanner.nextLine();
                                            continue;
                                        }
                                    } else if (movieLibraryChoice == 3) {
                                        userController.showMoviesInLibrary(currentUser);
                                    } else {
                                        System.err.println("Ви ввели невірний пункт!");
                                    }
                                    break;
                                case 2:
                                    // Опції для роботи з фільмами
                                    System.out.println("1. Переглянути всі фільми");
                                    System.out.println("2. Пошук фільму по ID");
                                    int moviesChoice;
                                    try {
                                        moviesChoice = scanner.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.err.print("Введіть цифру!\n");
                                        scanner.nextLine();
                                        continue;
                                    }

                                    if (moviesChoice == 1) {
                                        movieLibraryController.showAllMovies();
                                    } else if (moviesChoice == 2) {
                                        System.out.println("Введіть айді:");
                                        try {
                                            moviesChoice = scanner.nextInt();
                                            movieLibraryController.findMovieByID(moviesChoice);
                                        } catch (InputMismatchException e) {
                                            System.err.print("Введіть цифру!\n");
                                            scanner.nextLine();
                                            continue;
                                        }
                                    } else {
                                        System.err.println("Ви ввели невірний пункт!");
                                    }
                                    break;
                                // Додаткові опції для роботи з акторами, режисерами, категоріями
                                case 3:
                                    // Опції для роботи з акторами
                                    System.out.println("1. Переглянути всіх акторів");
                                    System.out.println("2. Пошук актора");
                                    int actorsChoice;
                                    try {
                                        actorsChoice = scanner.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.err.print("Введіть цифру!\n");
                                        scanner.nextLine();
                                        continue;
                                    }

                                    if (actorsChoice == 1) {
                                        movieLibraryController.showAllActors();
                                    } else if (actorsChoice == 2) {
                                        System.out.println("Введіть айді:");
                                        try {
                                            actorsChoice = scanner.nextInt();
                                            movieLibraryController.findActorByID(actorsChoice);
                                        } catch (InputMismatchException e) {
                                            System.err.print("Введіть цифру!\n");
                                            scanner.nextLine();
                                            continue;
                                        }
                                    } else {
                                        System.err.println("Ви ввели невірний пункт!");
                                    }
                                    break;
                                case 4:
                                    // Опції для роботи з режисерами
                                    System.out.println("1. Переглянути всіх режисерів");
                                    System.out.println("2. Пошук режисера");
                                    int directorsChoice;
                                    try {
                                        directorsChoice = scanner.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.err.print("Введіть цифру!\n");
                                        scanner.nextLine();
                                        continue;
                                    }

                                    if (directorsChoice == 1) {
                                        movieLibraryController.showAllDirectors();
                                    } else if (directorsChoice == 2) {
                                        System.out.println("Введіть айді:");
                                        try {
                                            directorsChoice = scanner.nextInt();
                                            movieLibraryController.findDirectorByID(
                                                directorsChoice);
                                        } catch (InputMismatchException e) {
                                            System.err.print("Введіть цифру!\n");
                                            scanner.nextLine();
                                            continue;
                                        }
                                    } else {
                                        System.err.println("Ви ввели невірний пункт!");
                                    }
                                    break;
                                case 5:
                                    // Опції для роботи з категоріями
                                    System.out.println("1. Переглянути всі категорії");
                                    System.out.println("2. Пошук категорії");
                                    int categoriesChoice;
                                    try {
                                        categoriesChoice = scanner.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.err.print("Введіть цифру!\n");
                                        scanner.nextLine();
                                        continue;
                                    }

                                    if (categoriesChoice == 1) {
                                        movieLibraryController.showAllCategories();
                                    } else if (categoriesChoice == 2) {
                                        System.out.println("Введіть айді:");
                                        try {
                                            categoriesChoice = scanner.nextInt();
                                            movieLibraryController.findCategoryByID(
                                                categoriesChoice);
                                        } catch (InputMismatchException e) {
                                            System.err.print("Введіть цифру!\n");
                                            scanner.nextLine();
                                            continue;
                                        }
                                    } else {
                                        System.err.println("Ви ввели невірний пункт!");
                                    }
                                    break;
                                case 6:
                                    // Перегляд інформації про обліковий запис користувача
                                    userController.showInfoAboutAccount(currentUser);
                                    break;
                                case 7:
                                    // Вихід з програми
                                    System.exit(0);
                                    break;
                                case 8:
                                    // Перегляд інформації про всіх користувачів (доступно для адміністратора)
                                    if (currentUser.getRole().getRole().equals("Адмін")) {
                                        userController.showAllAccounts();
                                    } else {
                                        System.err.println("У вас недостатньо прав!");
                                    }
                                    break;
                                case 9:
                                    // Видалення користувача (доступно для адміністратора)
                                    if (currentUser.getRole().getRole().equals("Адмін")) {
                                        System.out.println("Введіть ID користувача");
                                        try {
                                            choice = scanner.nextInt();
                                            userController.deleteUser(choice);
                                        } catch (InputMismatchException e) {
                                            System.err.print("Введіть цифру!\n");
                                            scanner.nextLine();
                                            continue;
                                        }
                                    } else {
                                        System.out.println(
                                            Ansi.ansi().fgRed().a("У вас недостатньо прав!")
                                                .reset());
                                    }
                                    break;
                                default:
                                    System.out.println(
                                        Ansi.ansi().fgRed().a("Ви обрали невірний пункт меню!")
                                            .reset());
                            }
                        } else {
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println(
                        Ansi.ansi().fgRed().a("Ви обрали невірний пункт меню!").reset());
            }

        }

    }
}


