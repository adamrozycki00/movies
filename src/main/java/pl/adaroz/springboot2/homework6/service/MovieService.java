package pl.adaroz.springboot2.homework6.service;

import org.springframework.stereotype.Service;
import pl.adaroz.springboot2.homework6.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    List<Movie> movies;

    public MovieService() {
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
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
