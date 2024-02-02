package com.nightfury.movielibrary.view.controller;

import com.nightfury.movielibrary.model.impl.Actor;
import com.nightfury.movielibrary.model.impl.Category;
import com.nightfury.movielibrary.model.impl.Director;
import com.nightfury.movielibrary.model.impl.Movie;
import com.nightfury.movielibrary.model.impl.Tag;
import com.nightfury.movielibrary.service.impl.ActorService;
import com.nightfury.movielibrary.service.impl.CategoryService;
import com.nightfury.movielibrary.service.impl.DirectorService;
import com.nightfury.movielibrary.service.impl.MovieService;
import com.nightfury.movielibrary.service.impl.TagService;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас контролера для взаємодії з функціоналом бібліотеки фільмів.
 */
public class MovieLibraryController {

    private final MovieService movieService;
    private final ActorService actorService;
    private final DirectorService directorService;
    private final CategoryService categoryService;
    private final TagService tagService;

    private List<Movie> movieList;
    private List<Actor> actorList;
    private List<Director> directorList;
    private List<Category> categoryList;
    private List<Tag> tagList;

    /**
     * Конструктор, який ініціалізує сервіси та списки сутностей і конфігурує їх.
     */

    public MovieLibraryController() {
        this.movieService = new MovieService();
        this.actorService = new ActorService();
        this.directorService = new DirectorService();
        this.categoryService = new CategoryService();
        this.tagService = new TagService();
        this.movieList = new ArrayList<>();
        this.actorList = new ArrayList<>();
        this.directorList = new ArrayList<>();
        this.categoryList = new ArrayList<>();
        this.tagList = new ArrayList<>();
        configureAllEntities();
    }

    /**
     * Отримати список фільмів.
     *
     * @return Список фільмів.
     */
    public List<Movie> getMovieList() {
        return movieList;
    }

    /**
     * Отримати список акторів.
     *
     * @return Список акторів.
     */
    public List<Actor> getActorList() {
        return actorList;
    }

    /**
     * Отримати список режисерів.
     *
     * @return Список режисерів.
     */
    public List<Director> getDirectorList() {
        return directorList;
    }

    /**
     * Отримати список категорій.
     *
     * @return Список категорій.
     */
    public List<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * Отримати список тегів.
     *
     * @return Список тегів.
     */
    public List<Tag> getTagList() {
        return tagList;
    }

    /**
     * Знайти фільм за назвою.
     *
     * @param name Назва фільму.
     */
    public void findMovieByName(String name) {
        List<Movie> movies = movieService.findByName(name);
        if (movies != null && !movies.isEmpty()) {
            movies.forEach(System.out::println);
        } else {
            System.err.println("Фільм не знайдено");
        }
    }

    /**
     * Показати всі фільми.
     */
    public void showAllMovies() {
        movieList.forEach(System.out::println);
    }

    /**
     * Знайти фільм за айді.
     *
     * @param ID айді фільму.
     */
    public void findMovieByID(int ID) {
        if (ID >= 11 || ID <= 0) {
            System.err.println("Фільм не знайдено!");
            return;
        }
        Movie movie = movieService.findById(ID);
        System.out.println(movie);
    }

    /**
     * Показати всіх акторів.
     */
    public void showAllActors() {
        actorList.forEach(System.out::println);
    }

    /**
     * Знайти актора за айді.
     *
     * @param ID айді актора.
     */
    public void findActorByID(int ID) {
        if (ID >= 21 || ID <= 0) {
            System.err.println("Актора не знайдено!");
            return;
        }
        Actor actor = actorService.findById(ID);
        System.out.println(actor);
    }

    /**
     * Показати всіх режисерів.
     */
    public void showAllDirectors() {
        directorList.forEach(System.out::println);
    }

    /**
     * Знайти режисера за айді.
     *
     * @param ID айді режисера.
     */
    public void findDirectorByID(int ID) {
        if (ID >= 11 || ID <= 0) {
            System.err.println("Режисера не знайдено!");
            return;
        }
        Director director = directorService.findById(ID);
        System.out.println(director);
    }

    /**
     * Показати всі категорії.
     */
    public void showAllCategories() {
        categoryList.forEach(System.out::println);
    }

    /**
     * Знайти категорію за айді.
     *
     * @param ID айді категорії.
     */
    public void findCategoryByID(int ID) {
        if (ID >= 9 || ID <= 0) {
            System.err.println("Категорію не знайдено!");
            return;
        }
        Category category = categoryService.findById(ID);
        System.out.println(category);
    }

    /**
     * Конфігурує всі сутності в бібліотеці фільмів шляхом виклику окремих методів конфігурації.
     */
    private void configureAllEntities() {
        configureMovies();
        configureCategories();
        configureTags();
        configureActors();
        configureDirectors();
    }

    /**
     * Конфігурує фільми, додаючи до них акторів, режисерів, категорії та теги з відповідних
     * сервісів.
     */
    private void configureMovies() {
        List<Movie> movies = movieService.getMoviesCollection();
        List<Actor> actors = actorService.getActorsCollection();
        List<Director> directors = directorService.getDirectorsCollection();
        List<Category> categories = categoryService.getCategoriesCollection();
        List<Tag> tags = tagService.getTagsCollection();

        for (Movie movie : movies) {
            for (int actorId : movie.getActorsID()) {
                for (Actor actor : actors) {
                    if (actor.getId() == actorId) {
                        movie.getActors().add(actor);
                        break;
                    }
                }
            }

            for (int directorId : movie.getDirectorsID()) {
                for (Director director : directors) {
                    if (director.getId() == directorId) {
                        movie.getDirectors().add(director);
                        break;
                    }
                }
            }

            for (int categoryId : movie.getCategoriesID()) {
                for (Category category : categories) {
                    if (category.getId() == categoryId) {
                        movie.getCategories().add(category);
                        break;
                    }
                }
            }

            for (int tagId : movie.getTagsID()) {
                for (Tag tag : tags) {
                    if (tag.getId() == tagId) {
                        movie.getTags().add(tag);
                        break;
                    }
                }
            }
        }

        this.movieList.addAll(movies);
    }

    /**
     * Конфігурує акторів, додаючи до них фільми з фільмографії з відповідних сервісів.
     */
    private void configureActors() {
        List<Actor> actors = actorService.getActorsCollection();
        List<Movie> movies = movieService.getMoviesCollection();

        for (Actor actor : actors) {
            for (Movie movie : movies) {
                if (containsId(movie.getActorsID(), actor.getId())) {
                    actor.getFilmography().add(movie);
                }
            }
        }

        this.actorList.addAll(actors);
    }

    /**
     * Конфігурує режисерів, додаючи до них фільми з фільмографії з відповідних сервісів.
     */
    private void configureDirectors() {
        List<Director> directors = directorService.getDirectorsCollection();
        List<Movie> movies = movieService.getMoviesCollection();

        for (Director director : directors) {
            for (Movie movie : movies) {
                if (containsId(movie.getDirectorsID(), director.getId())) {
                    director.getFilmography().add(movie);
                }
            }
        }

        this.directorList.addAll(directors);
    }

    /**
     * Конфігурує категорії, додаючи до них фільми з відповідними ID з відповідних сервісів.
     */
    private void configureCategories() {
        List<Category> categories = categoryService.getCategoriesCollection();
        List<Movie> movies = movieService.getMoviesCollection();

        for (Category category : categories) {
            for (Movie movie : movies) {
                if (containsId(movie.getCategoriesID(), category.getId())) {
                    category.getFilmscollection().add(movie);
                }
            }
        }

        this.categoryList.addAll(categories);
    }

    /**
     * Конфігурує теги, додаючи їх до списку тегів з відповідного сервісу.
     */
    private void configureTags() {
        List<Tag> tags = tagService.getTagsCollection();
        this.tagList.addAll(tags);
    }

    /**
     * Перевіряє, чи міститься певний ID у масиві ID.
     *
     * @param array Масив ID для перевірки.
     * @param id    ID, яке треба перевірити.
     * @return true, якщо ID міститься у масиві, в іншому випадку - false.
     */
    private boolean containsId(int[] array, int id) {
        for (int element : array) {
            if (element == id) {
                return true;
            }
        }
        return false;
    }


}
