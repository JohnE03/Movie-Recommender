package org.example;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserIntegrationTest {
    private static List<String> movies;
    @BeforeClass
    public static void setUp() {
        movies = new ArrayList<>();
        movies.add("TSR001");
        movies.add("TDK003");
        movies.add("TG002");
    }

    @After
    public void tearDown() {User.clearUsersList();}

    @Test
    public void userConstructorTest() {
        new User("Mina Sameh","12345678M",movies);
        User u=User.getUsersList().get(0);
        assertEquals("Mina Sameh",u.getUserName());
        assertEquals("12345678M",u.getUserId());
        new User("John Emile","12345678J",movies);
        assertEquals(2, User.getUsersList().size());
    }

    @Test
    public void userNameCheck1() {
        User user = new User("Ahmed Hany","12345678X",movies);
        assertTrue(Recommender.userNameCheck(user.getUserName()));
    }
    @Test
    public void userNameCheck2() {
        User user = new User(" Ahmed Hany","12345678X",movies);
        assertFalse(Recommender.userNameCheck(user.getUserName()));
    }
    @Test
    public void userNameCheck3() {
        User user = new User("Ahmed Hany1","12345678X",movies);
        assertFalse(Recommender.userNameCheck(user.getUserName()));
    }


    @Test
    public void userIdCheck1() {
        User user = new User("Ahmed Hany","12345678X",movies);
        assertTrue(Recommender.userIdCheck(user.getUserId()));
    }
    @Test
    public void userIdCheck2() {//not 9 characters
        User user = new User("Ahmed Hany","1234567X",movies);
        assertFalse(Recommender.userIdCheck(user.getUserId()));
    }
    @Test
    public void userIdCheck3() {//not unique
        User user = new User("John Shoukry","12345678X",movies);
        assertFalse(Recommender.userIdCheck(user.getUserId()));
    }
    @Test
    public void userIdCheck4() {//more than 2 characters
        User user = new User("Ahmed Hany","1234567XX",movies);
        assertFalse(Recommender.userIdCheck(user.getUserId()));
    }
    @Test
    public void userIdCheck5() {//special character
        User user = new User("Ahmed Hany","12345678@",movies);
        assertFalse(Recommender.userIdCheck(user.getUserId()));
    }
}