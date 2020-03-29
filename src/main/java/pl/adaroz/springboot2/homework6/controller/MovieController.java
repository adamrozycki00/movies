package pl.adaroz.springboot2.homework6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.adaroz.springboot2.homework6.model.Movie;
import pl.adaroz.springboot2.homework6.service.MovieService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Movie> getMovies() {
        return service.getMovies();
    }

    @PostMapping("")
    public void addMovie(@RequestBody Movie movie,
                         HttpServletResponse response) throws IOException {
        service.addMovie(movie);
        response.sendRedirect("/movies");
    }

}
