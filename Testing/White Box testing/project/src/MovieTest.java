import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MovieTest {

    @Before
    public void setUp() {
        Movie.getMovies().clear();
        Movie.count = 0;
    }

    @Test
    public void testConstructorWithManualId() {
        Movie m = new Movie("The Godfather", "TG001", Arrays.asList("Crime", "Drama"));

        assertEquals("The Godfather", m.getTitle());
        assertEquals("TG001", m.getId());
        assertEquals(Arrays.asList("Crime", "Drama"), m.getGenre());
        assertEquals(1, Movie.getCount());
        assertEquals(1, Movie.getMovies().size());
    }


    @Test
    public void testMultipleAutoIdGeneration() {
        Movie m1 = new Movie("The Dark Knight", Arrays.asList("Action", "Drama"));
        Movie m2 = new Movie("Fight Club", Arrays.asList("Drama"));

        assertEquals("TDK1", m1.getId());
        assertEquals("FC2", m2.getId());
    }

    @Test
    public void testStaticMovieListTracking() {
        Movie m1 = new Movie("Inception", "I001", Arrays.asList("Thriller", "Action"));
        Movie m2 = new Movie("Titanic", "T002", Arrays.asList("Romance", "Drama"));

        List<Movie> allMovies = Movie.getMovies();
        assertEquals(2, allMovies.size());
        assertTrue(allMovies.contains(m1));
        assertTrue(allMovies.contains(m2));
    }

    @Test
    public void testGetCountTracksCorrectly() {
        assertEquals(0, Movie.getCount());
        new Movie("Joker", Arrays.asList("Crime", "Drama"));
        assertEquals(1, Movie.getCount());
        new Movie("Up", Arrays.asList("Animation", "Adventure"));
        assertEquals(2, Movie.getCount());
    }
}
