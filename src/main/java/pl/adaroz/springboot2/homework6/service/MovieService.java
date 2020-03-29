package pl.adaroz.springboot2.homework6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adaroz.springboot2.homework6.model.Movie;

import java.util.ArrayList;
import java.util.List;

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

    public void addMovie(Movie movie) {
        movies.add(movie);
        mailService.sendSimpleMessage("adamrozycki00@gmail.com",
                "Your movie has been added",
                "Title\nDirector\nYear");
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
