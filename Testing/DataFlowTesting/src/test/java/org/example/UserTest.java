package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    @BeforeEach
    public void setup() {
        Movie.resetMovies(); // clear static movie list
        User.clearUsersList();  // clear static user list
    }

    @Test
    public void testUserInitializationAndGetterMethods() {
        // First create the movies
        Movie m1 = new Movie("Iron Man", "IM123", Arrays.asList("Action"));
        Movie m2 = new Movie("The Ring", "TR456", Arrays.asList("Horror"));

        // Then create the user referencing an existing movie ID
        List<String> likedMovies = Arrays.asList("IM123");
        User u = new User("John Doe", "12345678A", likedMovies);

        // Validate basic getters
        assertEquals("John Doe", u.getUserName());
        assertEquals("12345678A", u.getUserId());
        assertEquals(likedMovies, u.getMoviesId());
    }

    @Test
    public void testUserRecommendationsBasedOnGenres() {
        Movie m1 = new Movie("Inception", "I123", Arrays.asList("Sci-Fi", "Action"));
        Movie m2 = new Movie("Interstellar", "IS456", Arrays.asList("Sci-Fi"));
        Movie m3 = new Movie("Titanic", "T789", Arrays.asList("Romance"));

        // User likes Inception (Sci-Fi, Action)
        User u = new User("Alice Smith", "11112222X", Arrays.asList("I123"));

        List<Movie> recommendations = u.getRecommendations();

        // Should recommend Interstellar, but not Titanic
        assertEquals(1, recommendations.size());
        assertEquals("Interstellar", recommendations.get(0).getTitle());
    }

    @Test
    public void testNoRecommendationsWhenGenresDoNotMatch() {
        Movie m1 = new Movie("Notebook", "NB001", Arrays.asList("Romance"));
        Movie m2 = new Movie("Avengers", "AV002", Arrays.asList("Action"));

        // User likes only Romance movie
        User u = new User("Bob Brown", "22223333Z", Arrays.asList("NB001"));

        List<Movie> recommendations = u.getRecommendations();
        // Avengers is Action, doesn't match Romance
        assertEquals(0, recommendations.size());
    }

    @Test
    public void testUserWithNoLikedMovies_EmptyRecommendations() {
        Movie m1 = new Movie("Joker", "JK001", Arrays.asList("Drama"));

        // User likes no movies
        User u = new User("Chris", "33334444K", new ArrayList<>());

        List<Movie> recommendations = u.getRecommendations();
        // No genres = no basis for recommendation
        assertTrue(recommendations.isEmpty());
    }

    @Test
    public void testStaticUserListPopulationAndReset() {
        assertEquals(0, User.getUsersList().size());

        User u1 = new User("Eve", "44445555M", Arrays.asList());
        User u2 = new User("Adam", "55556666N", Arrays.asList());

        assertEquals(2, User.getUsersList().size());

        User.clearUsersList();

        assertEquals(0, User.getUsersList().size());
    }
}

