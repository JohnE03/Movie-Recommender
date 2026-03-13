import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class RecommenderTest {

    @Before
    public void setUp() {
        // Clear static lists before each test
        Recommender.movieIdsNumbers.clear();
        Recommender.userIds.clear();
    }

    // ===== movieTitleCheck =====
    @Test
    public void testMovieTitleCheck_AllUppercase() {
        assertTrue(Recommender.movieTitleCheck("The Matrix"));
    }

    @Test
    public void testMovieTitleCheck_WordStartsLowercase() {
        assertFalse(Recommender.movieTitleCheck("the Matrix"));
    }

    @Test
    public void testMovieTitleCheck_MixedCaseWord() {
        assertFalse(Recommender.movieTitleCheck("The matrix"));
    }

    @Test
    public void testMovieTitleCheck_SingleWordUppercase() {
        assertTrue(Recommender.movieTitleCheck("Interstellar"));
    }

    // ===== movieIdLettersCheck =====
    @Test
    public void testMovieIdLettersCheck_CorrectInitials() {
        assertTrue(Recommender.movieIdLettersCheck(new String[]{"The Matrix", "TM123"}));
    }

    @Test
    public void testMovieIdLettersCheck_WrongInitials() {
        assertFalse(Recommender.movieIdLettersCheck(new String[]{"The Matrix", "TX123"}));
    }

    @Test
    public void testMovieIdLettersCheck_SingleWordTitle() {
        assertTrue(Recommender.movieIdLettersCheck(new String[]{"Titanic", "T456"}));
    }

    // ===== movieIdNumbersCheck =====
    @Test
    public void testMovieIdNumbersCheck_ValidAndUnique() {
        assertTrue(Recommender.movieIdNumbersCheck("TM123"));
    }

    @Test
    public void testMovieIdNumbersCheck_Duplicate() {
        Recommender.movieIdNumbersCheck("TM123");
        assertFalse(Recommender.movieIdNumbersCheck("AB123"));
    }

    @Test
    public void testMovieIdNumbersCheck_TooFewDigits() {
        assertFalse(Recommender.movieIdNumbersCheck("TM12"));
    }

    @Test
    public void testMovieIdNumbersCheck_TooManyDigits() {
        assertFalse(Recommender.movieIdNumbersCheck("TM1234"));
    }

    // ===== userNameCheck =====
    @Test
    public void testUserNameCheck_ValidName() {
        assertTrue(Recommender.userNameCheck("Hassan Ali"));
    }

    @Test
    public void testUserNameCheck_LeadingSpace() {
        assertFalse(Recommender.userNameCheck(" Hassan Ali"));
    }

    @Test
    public void testUserNameCheck_NumbersInName() {
        assertFalse(Recommender.userNameCheck("Hassan123"));
    }

    @Test
    public void testUserNameCheck_SpecialCharInName() {
        assertFalse(Recommender.userNameCheck("Hassan@Ali"));
    }

    // ===== userIdCheck =====
    @Test
    public void testUserIdCheck_ValidAndUnique() {
        assertTrue(Recommender.userIdCheck("12345678X"));
    }

    @Test
    public void testUserIdCheck_DuplicateId() {
        Recommender.userIdCheck("12345678X");
        assertFalse(Recommender.userIdCheck("12345678X"));
    }

    @Test
    public void testUserIdCheck_ShortLength() {
        assertFalse(Recommender.userIdCheck("1234567X")); // 8 chars
    }

    @Test
    public void testUserIdCheck_NonAlphanumericEnd() {
        assertFalse(Recommender.userIdCheck("12345678@"));
    }

    @Test
    public void testUserIdCheck_LettersInDigits() {
        assertFalse(Recommender.userIdCheck("ABCDEFGHX"));
    }

}
