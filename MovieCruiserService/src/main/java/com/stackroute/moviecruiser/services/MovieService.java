package com.stackroute.moviecruiser.services;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;

import java.util.List;

public interface MovieService {

    boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;

    boolean updateMovie(Movie updateMovie) throws MovieNotFoundException;

    boolean deleteMovie(Integer id) throws MovieNotFoundException;

    Movie getMovieById(Integer id) throws MovieNotFoundException;

    List<Movie> getMyMovies(String userId);
}
