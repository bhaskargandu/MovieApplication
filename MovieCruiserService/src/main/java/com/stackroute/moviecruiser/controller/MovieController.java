package com.stackroute.moviecruiser.controller;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import com.stackroute.moviecruiser.services.MovieService;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/movieservice")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(MovieController.class);
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movie")
    public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie, HttpServletRequest request){
        ResponseEntity responseEntity;
        log.info("save movie");
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String token = authHeader.substring(7);
        String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
        log.info("userId="+userId);

        try {
            movie.setUserId(userId);
            movieService.saveMovie(movie);
            responseEntity = new ResponseEntity(movie, HttpStatus.CREATED);
        }catch (MovieAlreadyExistsException e){
            responseEntity = new ResponseEntity("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.CONFLICT);

        }
        return responseEntity;
    }

    @PutMapping(path = "/movie/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable("id") final Integer id,@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try {
            movieService.updateMovie(movie);
            responseEntity = new ResponseEntity(movie,HttpStatus.OK);
        }catch (MovieNotFoundException e){
            responseEntity = new ResponseEntity("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.NOT_FOUND);
        }

        return  responseEntity;
    }

    @DeleteMapping(value = "/movie/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable("id") final int id) {
        ResponseEntity<?> responseEntity;
        try {
            movieService.deleteMovie(id);
            responseEntity = new ResponseEntity<String>("movie deleted successfully", HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
                    HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @GetMapping(path = "/movie/{id}")
    public ResponseEntity<?> fetchMovieById(@PathVariable("id") final int id) {
        ResponseEntity<?> responseEntity;
        Movie thisMovie = null;
        try {
            thisMovie = movieService.getMovieById(id);
        } catch (MovieNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        responseEntity = new ResponseEntity<Movie>(thisMovie, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/movies")
    public ResponseEntity fetchMyMovies(final HttpServletRequest request, final HttpServletResponse response){
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String token = authHeader.substring(7);
        String userId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
        log.info("userId="+userId);

        ResponseEntity responseEntity;
        List<Movie> movies = movieService.getMyMovies(userId);
        responseEntity = new ResponseEntity(movies, HttpStatus.OK);
        return responseEntity;
    }
}
