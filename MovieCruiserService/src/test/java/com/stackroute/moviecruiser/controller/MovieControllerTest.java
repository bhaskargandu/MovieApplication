package com.stackroute.moviecruiser.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.services.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private transient MockMvc mvc;

    @MockBean
    private transient MovieService service;

    private transient Movie movie;

    static List<Movie> movies;

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU0MDE5NjMwN30.a_J-cOzOL0QD7XdzivbcV1sQaszLndIFPDNQ_AiK0Vg";

    @Before
    public void setUp(){
        movies = new ArrayList<>();
        movie = new Movie(1,"POC","good movie","www.abc.com","2015-03-03",45.5,112,"user1");
        movies.add(movie);
        movie = new Movie(2,"POC-2","good movie","www.abc.com","2015-03-03",45.5,112,"user1");
        movies.add(movie);
    }

    @Test
    public void testSaveNewMovieSuccess() throws Exception{
        when(service.saveMovie(movie)).thenReturn(true);
        mvc.perform(post("/api/v1/movieservice/movie")
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(movie)))
                .andExpect(status().isCreated());
        verify(service,times(1)).saveMovie(Mockito.any(Movie.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testUpdateMovieSuccess() throws Exception{
        movie.setComments("not so good movie");
        when(service.updateMovie(movie)).thenReturn(true);
        mvc.perform(put("/api/v1/movieservice/movie/{id}",1)
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(movie)))
                .andExpect(status().isOk());
        verify(service,times(1)).updateMovie(Mockito.any(Movie.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDeleteMovieById() throws Exception{
        when(service.deleteMovie(1)).thenReturn(true);
        mvc.perform(delete("/api/v1/movieservice/movie/{id}",1).header(HttpHeaders.AUTHORIZATION,"Bearer "+token)).andExpect(status().isOk());
        verify(service,times(1)).deleteMovie(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testFetchMovieById() throws Exception{
        when(service.getMovieById(1)).thenReturn(movies.get(0));
        mvc.perform(get("/api/v1/movieservice/movie/{id}",1).header(HttpHeaders.AUTHORIZATION,"Bearer "+token)).andExpect(status().isOk());
        verify(service,times(1)).getMovieById(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testGetMyMovies() throws Exception {
        when(service.getMyMovies("user1")).thenReturn(movies);
        mvc.perform(get("/api/v1/movieservice/movies", 1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(service,times(1)).getMyMovies("user1");
        verifyNoMoreInteractions(service);
    }

    private String jsonToString(Object object){
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(object);
        }catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }
}