package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecommenderTest {

    @BeforeEach
    void setup() {
        User.clearUsersList();
        Movie.resetMovies();
        Recommender.clearUserIds();
        Recommender.clearMovieIds();;
    }

    // movieTitleCheck
    @Test
    void testMovieTitleCheck() {
        assertTrue(Recommender.movieTitleCheck("The Godfather"));
        assertFalse(Recommender.movieTitleCheck("the godfather"));
        assertFalse(Recommender.movieTitleCheck("The godfather"));
    }

    // movieIdLettersCheck
    @Test
    void testMovieIdLettersCheck() {
        String[] line = {"The Matrix", "TM123"};
        assertTrue(Recommender.movieIdLettersCheck(line));

        String[] badLine = {"The Matrix", "MX123"};
        assertFalse(Recommender.movieIdLettersCheck(badLine));
    }

    // p-use of movieIdsNumbers
    @Test
    void testMovieIdNumbersCheck() {
        assertTrue(Recommender.movieIdNumbersCheck("TM123")); // First use
        assertFalse(Recommender.movieIdNumbersCheck("AB123")); // Duplicate numbers
        assertFalse(Recommender.movieIdNumbersCheck("AB12"));  // Too short
    }

    // Test for valid user name (userNameCheck)
    @Test
    void testUserNameCheck() {
        assertTrue(Recommender.userNameCheck("John Smith"));
        assertFalse(Recommender.userNameCheck(" john")); // starts with space
        assertFalse(Recommender.userNameCheck("John123")); // contains digits
    }

    // p-use of userIds
    @Test
    void testUserIdCheck() {
        assertTrue(Recommender.userIdCheck("12345678A")); // Valid
        assertFalse(Recommender.userIdCheck("1234567"));  // Too short
        assertFalse(Recommender.userIdCheck("A23456789")); // Starts with letter
        assertFalse(Recommender.userIdCheck("12345678A")); // Duplicate
    }

    // userGenres def-use
    @Test
    void testRecommendationBasedOnGenres() {
        // Movie the user has seen
        Movie m1 = new Movie("Fast Fury", "FF101", Arrays.asList("Action", "Thriller"));
        // Another movie in same genre
        Movie m2 = new Movie("Speed Zone", "SZ102", Arrays.asList("Action"));
        // Irrelevant genre
        Movie m3 = new Movie("Love Story", "LS103", Arrays.asList("Romance"));

        List<String> likedIds = Arrays.asList("FF101");
        User user = new User("Alice Johnson", "12345678A", likedIds);

        List<Movie> recommendations = user.getRecommendations();

        assertTrue(recommendations.contains(m2)); // Same genre
        assertFalse(recommendations.contains(m3)); // Different genre
    }

    // Ensure that userGenres is populated correctly from liked movies (def-use)
    @Test
    void testUserGenresPopulation() {
        Movie m1 = new Movie("Fear Night", "FN001", Arrays.asList("Horror"));
        List<String> liked = Arrays.asList("FN001");
        User user = new User("Sam Miller", "23456789B", liked);

        List<Movie> recs = user.getRecommendations(); // triggers use of userGenres
        assertEquals(Set.of("Horror"), userGenresFromPrivate(user));
    }

    // Helper method to access private userGenres using reflection
    @SuppressWarnings("unchecked")
    private Set<String> userGenresFromPrivate(User user) {
        try {
            var field = User.class.getDeclaredField("userGenres");
            field.setAccessible(true);
            return (Set<String>) field.get(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
