package pl.adaroz.springboot2.homework6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adaroz.springboot2.homework6.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MovieService {

    List<Movie> movies;
    MailService mailService;

    @Autowired
    public MovieService(MailService mailService) {
        this.mailService = mailService;

        movies = new ArrayList<>();
        movies.add(new Movie("Jaws", "Spielberg", 1975));
        movies.add(new Movie("Raiders of the Lost Ark", "Spielberg", 1981));
        movies.add(new Movie("Titanic", "Cameron", 1997));
        movies.add(new Movie("Gladiator", "Scott", 2000));
        movies.add(new Movie("Apocalypse Now", "Coppola", 1979));
        movies.add(new Movie("Star Wars", "Lucas", 1977));
    }

    public void addMovie(Movie movie, String email) {
        boolean movieAdded = movies.add(movie);
        boolean emailValid = isValid(email);

        if (emailValid) {
            String title =
                    movieAdded ?
                    "Your movie has been added" :
                    "Adding your movie failed";
            mailService.sendSimpleMessage(email, title, getMoviesString());
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    private String getMoviesString() {
        StringBuilder moviesString = new StringBuilder();
        for (Movie m : movies) {
            moviesString.append(m.getTitle());
            moviesString.append(", ");
            moviesString.append(m.getDirector());
            moviesString.append(", ");
            moviesString.append(m.getYear());
            moviesString.append("\n------------------------------\n");
        }
        return moviesString.toString();
    }

    //https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    private boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
