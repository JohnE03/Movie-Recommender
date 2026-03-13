package org.example;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class RecommenderIntegrationTest {
    User user;
    InputStream originalIn = System.in;
    ByteArrayInputStream bais;
    String input="";

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        new Movie("The Shawshank Redemption","TSR001",genre);
        genre.add("Crime");
        new Movie("The Godfather","TG002",genre);
        genre.add("Action");
        new Movie("The Dark Knight","TDK003",genre);
    }

    @After
    public void tearDown() {
        Recommender.clearMovieIds();
        Recommender.clearUserIds();
        User.clearUsersList();
        Movie.resetMovies();

        System.setIn(originalIn);
    }

    @Test
    public void testFileReader() {
        input="movies.txt\n";
        bais=new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);
        List<String> expected= new ArrayList<>();
        expected.add("The Shawshank Redemption, TSR001");
        expected.add("Drama");
        expected.add("The Godfather, TG002");
        expected.add("Crime, Drama");
        expected.add("The Dark Knight, TDK003");
        expected.add("Action, Crime, Drama");
        List<String> data=Recommender.fileReader("Enter data location: ");
        assertArrayEquals(expected.toArray(), data.toArray());
    }

    @Test
    public void testWriteErrorMessage() {
        Recommender.writeErrorMsg("Error");
    }

    @Test
    public void getRecommendations() {
        List<String> movies=new ArrayList<>();
        movies.add("TSR001");
        movies.add("TDK003");
        user=new User("Hassan Ali","12345678X",movies);
        List<Movie> recommendations=user.getRecommendations();

        Vector<String> expected=new Vector<>();
        expected.add("The Godfather");
        for(int i=0;i<recommendations.size();i++){
            assertEquals(recommendations.get(i).getTitle(),expected.get(i));
        }
    }
    @Test
    public void getRecommendations2() {
        List<String> movies=new ArrayList<>();
        movies.add("TG002");
        user=new User("Ali Mohamed","87654321W",movies);
        List<Movie> recommendations=user.getRecommendations();

        Vector<String> expected=new Vector<>();
        expected.add("The Shawshank Redemption");
        expected.add("The Dark Knight");
        for(int i=0;i<recommendations.size();i++){
            assertEquals(recommendations.get(i).getTitle(),expected.get(i));
        }
    }
    @Test
    public void getRecommendations3() {
        List<String> movies=new ArrayList<>();
        movies.add("TDK003");
        user=new User("John Shoukry","87654341W",movies);
        List<Movie> recommendations=user.getRecommendations();

        Vector<String> expected=new Vector<>();
        expected.add("The Shawshank Redemption");
        expected.add("The Godfather");
        for(int i=0;i<recommendations.size();i++){
            assertEquals(recommendations.get(i).getTitle(),expected.get(i));
        }
    }

}