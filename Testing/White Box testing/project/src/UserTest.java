import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class UserTest {

    @Before
    public void setUp() {
        // Reset static state
        Movie.moviesList.clear();
        User.getUsersList().clear();
    }

    @Test
    public void testUserConstructor_AddsToUserList() {
        Movie m1 = new Movie("The Matrix", "TM123", Arrays.asList("Action", "Sci-Fi"));
        User u = new User("Hassan Ali", "12345678X", List.of("TM123"));

        assertEquals(1, User.getUsersList().size());
        assertEquals("Hassan Ali", User.getUsersList().get(0).getUserName());
    }

    @Test
    public void testGetUserMovies_PopulatesUserMoviesCorrectly() {
        Movie m1 = new Movie("Inception", "I123", Arrays.asList("Action", "Thriller"));
        Movie m2 = new Movie("Titanic", "T456", Arrays.asList("Romance", "Drama"));
        User u = new User("Ali Hassan", "87654321Y", Arrays.asList("I123", "T456"));

        assertEquals(2, u.getMoviesId().size());
        List<Movie> recommendations = u.getRecommendations();
        assertEquals(0, recommendations.size()); // All movies already watched
    }

    @Test
    public void testGetRecommendations_ReturnsMatchingGenre() {
        Movie m1 = new Movie("Interstellar", "I111", Arrays.asList("Sci-Fi"));
        Movie m2 = new Movie("The Shawshank Redemption", "TSR001", List.of("Drama"));
        Movie m3 = new Movie("The Godfather", "TG002", Arrays.asList("Crime", "Drama"));
        Movie m4 = new Movie("The Dark Knight", "TDK003", Arrays.asList("Action","Crime"));

        // User watches one Sci-Fi movie
        User u = new User("Ahmed", "11223344Z", List.of("TG002")); // Drama and Crime

        List<Movie> recs = u.getRecommendations();

        List<String> recommendedIds = new ArrayList<>();
        for (Movie m : recs) {
            recommendedIds.add(m.getId());
        }

        assertTrue(recommendedIds.contains("TSR001")); // Drama match
        assertFalse(recommendedIds.contains("TG002")); // Already watched
        assertTrue(recommendedIds.contains("TDK003")); // Crime match
        assertFalse(recommendedIds.contains("I111")); // no genre match
    }

    @Test
    public void testGetRecommendations_MultipleGenreMatches() {
        Movie m1 = new Movie("Movie1", "M001", Arrays.asList("Action"));
        Movie m2 = new Movie("Movie2", "M002", Arrays.asList("Drama"));
        Movie m3 = new Movie("Movie3", "M003", Arrays.asList("Action", "Drama"));
        Movie m4 = new Movie("Movie4", "M004", Arrays.asList("Romance"));

        User u = new User("Fatima", "44556677X", Arrays.asList("M001", "M002")); // action and drama
        // so it should get recommended M003

        List<Movie> recs = u.getRecommendations();
        assertEquals(1, recs.size());
        assertEquals("M003", recs.get(0).getId());
    }

    @Test
    public void testGetRecommendations_NoMatches() {
        Movie m1 = new Movie("Movie1", "M001", Arrays.asList("Horror"));
        Movie m2 = new Movie("Movie2", "M002", Arrays.asList("Romance"));
        User u = new User("Lina", "99887766T", Arrays.asList("M001"));

        List<Movie> recs = u.getRecommendations();
        assertEquals(0, recs.size());
    }
}
