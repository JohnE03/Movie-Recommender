package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieTest {

    @BeforeEach
    public void setup() {
        Movie.resetMovies();
    }

    @Test
    public void testMovieConstructorAndGetters() {
        List<String> genres = Arrays.asList("Horror", "Mystery");
        Movie movie = new Movie("The Conjuring", "TC123", genres);

        assertEquals("The Conjuring", movie.getTitle());
        assertEquals("TC123", movie.getId());
        assertEquals(genres, movie.getGenre());
    }

    @Test
    public void testMovieListStaticTracking() {
        assertEquals(0, Movie.getMovies().size());

        new Movie("Avatar", "A001", Arrays.asList("Sci-Fi"));
        new Movie("Titanic", "T002", Arrays.asList("Romance"));

        List<Movie> allMovies = Movie.getMovies();
        assertEquals(2, allMovies.size());
        assertEquals("Avatar", allMovies.get(0).getTitle());
        assertEquals("Titanic", allMovies.get(1).getTitle());
    }

    @Test
    public void testMovieCountTracking() {
        assertEquals(0, Movie.getMovies().size());

        new Movie("Matrix", "M100", Arrays.asList("Sci-Fi"));
        new Movie("Jaws", "J101", Arrays.asList("Thriller"));

        assertEquals(2, Movie.getMovies().size());
    }

    @Test
    public void testMovieResetFunctionality() {
        new Movie("Toy Story", "TS321", Arrays.asList("Animation"));
        assertFalse(Movie.getMovies().isEmpty());

        Movie.resetMovies();
        assertTrue(Movie.getMovies().isEmpty());
    }
}
