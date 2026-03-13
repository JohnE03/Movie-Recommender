package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Movie {
    private String title;
    private List<String> genre;
    private String id="";

    static int count=0;
    static List<Movie> moviesList = new ArrayList<>(); //all movie
    //static Set<String> moviesIdsNumbers = new HashSet<>();

    public Movie(String title, String id, List<String> genre) {
        count++;
        this.title = title;
        this.id=id;
        this.genre = genre;
        moviesList.add(this);
        //moviesIdsNumbers.add(id.replaceAll("[A-Z]",""));
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    public static List<Movie> getMovies() {
        return moviesList;
    }

    //public static Set<String> getmoviesIdsNumbers(){ return moviesIdsNumbers; }

    public static void resetMovies() {moviesList.clear();}

}