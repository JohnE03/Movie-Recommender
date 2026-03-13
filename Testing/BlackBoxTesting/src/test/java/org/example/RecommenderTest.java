package org.example;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;

/* movies4.txt and users4.txt
are both control cases where each line
is valid to check if the program
runs correctly with valid test cases
 */

public class RecommenderTest {
    String path="";

    @Test
    public void recommenderTester1() throws IOException {
        for (int i = 0; i < 5; i++) {
            Recommender.movieIdsNumbers.clear();
            Recommender.userIds.clear();
            User.clearUsersList();
            Movie.resetMovies();

            String movieFilePath = "movies";
            path = movieFilePath + i + ".txt" + '\n' + "users4.txt" + '\n';
            ByteArrayInputStream bais = new ByteArrayInputStream(path.getBytes());
            System.setIn(bais);
            Recommender.scan = new Scanner(System.in);
            if(i<4)
            assertThrows(RuntimeException.class, () -> Recommender.main(null));
            else{
                Recommender.main(null);
            }
        }

    }

    @Test
    public void recommenderTester2() throws IOException {
        for(int count=0;count<5;count++) {
            Recommender.movieIdsNumbers.clear();
            Recommender.userIds.clear();
            User.clearUsersList();
            Movie.resetMovies();

            path = "movies4.txt" + '\n' + "users" + count +".txt" + '\n';
            ByteArrayInputStream bais2 = new ByteArrayInputStream(path.getBytes());
            System.setIn(bais2);
            Recommender.scan = new Scanner(System.in);
            if(count<4)
            assertThrows(RuntimeException.class, () -> Recommender.main(null));
            else{
                Recommender.main(null);
            }
        }
    }
}