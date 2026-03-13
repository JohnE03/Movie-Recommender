import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String userName;
    private String userId;
    private List<String> moviesId; //movie IDs

    private List<Movie> userMovies = new ArrayList<>();
    private Set<String> userGenres = new HashSet<>();

    static List<User> usersList = new ArrayList<>();

    public User(String userName, String userId, List<String> moviesId) {
        this.userName = userName;
        this.userId = userId;
        this.moviesId = moviesId;
        getUserMovies();
        usersList.add(this);
    }

    public String getUserName() {
        return userName;
    }
    public String getUserId() {
        return userId;
    }
    public List<String> getMoviesId() {
        return moviesId;
    }
    public static List<User> getUsersList(){return usersList;}

    public void getUserMovies(){
        for(String movieId:this.moviesId){
            for(Movie movie:Movie.getMovies()){
                if(movie.getId().equals(movieId)){
                    this.userMovies.add(movie);
                    this.userGenres.addAll(movie.getGenre());
                }
            }
        }
    }

    public List<Movie> getRecommendations(){
        List<Movie> recommendations=new ArrayList<>();

        for(Movie movie:Movie.getMovies()){
            if(moviesId.contains(movie.getId())) continue;

            for(String genre:movie.getGenre()){
                if(userGenres.contains(genre)){
                    recommendations.add(movie);
                    break;
                }
            }

        }

        return recommendations;
    }
}
