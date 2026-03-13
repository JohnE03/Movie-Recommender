package org.example;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class MovieIntegrationTest {

    @Test
    public void movieConstructorTest(){
        List<String> genres = new ArrayList<>();
        genres.add("Action");
        new Movie("The Shawshank Redemption","TSR001",genres);
        Movie temp=Movie.getMovies().get(0);
        assertEquals("The Shawshank Redemption",temp.getTitle());
        assertEquals("TSR001",temp.getId());
        genres.add("Action");
        new Movie("The Dark Knight","TDK003",genres);
        assertEquals(2, Movie.getMovies().size());
    }

    @Test
    public void movieIdNumbersCheck1() {
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        Movie movie = new Movie("The Shawshank Redemption","TSR001",genre);
        assertTrue(Recommender.movieIdNumbersCheck(movie.getId()));
    }
    @Test
    public void movieIdNumbersCheck2() { //unique check
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        genre.add("Crime");
        Movie movie2 = new Movie("The Godfather","TG001",genre);
        assertFalse(Recommender.movieIdNumbersCheck(movie2.getId()));
    }
    @Test
    public void movieIdNumbersCheck3() { //no of characters
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        genre.add("Crime");
        genre.add("Adventure");
        Movie movie = new Movie("Jumanji","J2345",genre);
        assertFalse(Recommender.movieIdNumbersCheck(movie.getId()));
    }

    @Test
    public void movieIdLettersCheck1() {
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        Movie movie = new Movie("The Shawshank Redemption","TSR001",genre);
        String[] letters={movie.getTitle(), movie.getId()};
        assertTrue(Recommender.movieIdLettersCheck(letters));
    }
    @Test
    public void movieIdLettersCheck2() {
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        Movie movie = new Movie("The Shawshank Redemption","tsr001",genre);
        String[] letters={movie.getTitle(), movie.getId()};
        assertFalse(Recommender.movieIdLettersCheck(letters));
    }
    @Test
    public void movieIdLettersCheck3() {
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        Movie movie = new Movie("The Shawshank Redemption","TSRX01",genre);
        String[] letters={movie.getTitle(), movie.getId()};
        assertFalse(Recommender.movieIdLettersCheck(letters));
    }

    @Test
    public void movieTitleCheck1() {
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        Movie movie = new Movie("The Shawshank Redemption","TSR001",genre);
        assertTrue(Recommender.movieTitleCheck(movie.getTitle()));
    }
    @Test
    public void movieTitleCheck2() {
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        Movie movie = new Movie("The shawshank Redemption","tsr001",genre);
        assertFalse(Recommender.movieTitleCheck(movie.getTitle()));
    }
}