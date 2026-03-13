package org.example;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IntegrationTesting {
    String input="";
    String path="";
    ByteArrayInputStream bais;

    @Test
    public void movieFileVerificationTest(){
        input="movies.txt";
        bais=new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);
        List<String> data=Recommender.movieFileVerification();
        assertTrue(data.size()>0);
        List<String> expected=new ArrayList<>();
        expected.add("The Shawshank Redemption, TSR001");
        expected.add("Drama");
        expected.add("The Godfather, TG002");
        expected.add("Crime, Drama");
        expected.add("The Dark Knight, TDK003");
        expected.add("Action, Crime, Drama");
        assertArrayEquals(expected.toArray(), data.toArray());
    }

    @Test
    public void writeRecommendationTest(){
        new Movie("The Shawshank Redemption", "TSR001", List.of("Drama"));
        new Movie("The Godfather", "TG002", List.of("Crime", "Drama"));
        new Movie("The Dark Knight", "TDK003", List.of("Action", "Crime", "Drama"));
        new User("Hassan Ali", "12345678X", List.of("TSR001", "TDK003"));
        new User("Ali Mohamed", "87654321W", List.of("TG002"));
        Recommender.writeRecommendationsToFile();
    }
}
