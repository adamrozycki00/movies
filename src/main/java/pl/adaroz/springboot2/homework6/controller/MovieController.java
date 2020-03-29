package pl.adaroz.springboot2.homework6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.adaroz.springboot2.homework6.aspect.MailSenderAfterAddingMovie;
import pl.adaroz.springboot2.homework6.aspect.MailSenderAspect;
import pl.adaroz.springboot2.homework6.model.Movie;
import pl.adaroz.springboot2.homework6.service.MovieService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;
    private MailSenderAspect mailService;

    @Autowired
    public MovieController(MovieService movieService,
                           MailSenderAspect mailService) {
        this.movieService = movieService;
        this.mailService = mailService;
    }

    @GetMapping("")
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping("")
    @MailSenderAfterAddingMovie
    public void addMovie(HttpServletResponse response,
                         @RequestBody Movie movie,
                         @RequestHeader String email
                         ) throws IOException {
        mailService.setTo(email);
        movieService.addMovie(movie);
        response.sendRedirect("/movies");
    }

}
