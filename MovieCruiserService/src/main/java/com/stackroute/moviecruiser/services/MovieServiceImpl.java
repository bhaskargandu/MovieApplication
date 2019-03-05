package com.stackroute.moviecruiser.services;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import com.stackroute.moviecruiser.repository.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final transient MovieRepository movieRepo;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepo) {
        super();
        this.movieRepo = movieRepo;
    }

    @Override
    public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
        final Optional<Movie> object = movieRepo.findById(movie.getId());
        if (object.isPresent()){
            throw new MovieAlreadyExistsException("Could Not save Movie , Movie already exists");
        }
        movieRepo.save(movie);
        return true;
    }

    @Override
    public boolean updateMovie(Movie updateMovie) throws MovieNotFoundException {
        final Movie movie = movieRepo.findById(updateMovie.getId()).orElse(null);
        if (movie == null) {
            throw new MovieNotFoundException("Could not update Movie,Movie not found!");
        }
        BeanUtils.copyProperties(updateMovie,movie);
        movieRepo.save(movie);
        return true;
    }


    @Override
    public boolean deleteMovie(Integer id) throws MovieNotFoundException {
        final Movie movie = movieRepo.findById(id).orElse(null);
        if (Objects.isNull(movie)){
            throw new MovieNotFoundException("Could not delete , Movie not found");
        }
        movieRepo.delete(movie);
        return true;
    }

    @Override
    public Movie getMovieById(Integer id) throws MovieNotFoundException {
        final Movie movie = movieRepo.findById(id).get();
        if (Objects.isNull(movie)){
            throw new MovieNotFoundException("Movie not found!");
        }
        return movie;
    }

    @Override
    public List<Movie> getMyMovies(String userId) {
        return movieRepo.findByUserId(userId);
    }
}
