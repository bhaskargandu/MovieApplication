package com.stackroute.moviecruiser.repository;

import com.stackroute.moviecruiser.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    List<Movie> findByUserId(String userId);
}
