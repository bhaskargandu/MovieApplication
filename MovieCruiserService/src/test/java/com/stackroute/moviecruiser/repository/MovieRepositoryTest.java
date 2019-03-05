package com.stackroute.moviecruiser.repository;

import com.stackroute.moviecruiser.domain.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class MovieRepositoryTest {

    @Autowired
    private transient MovieRepository repo;

    @Test
    public void testSaveMovie() throws Exception{
        repo.save(new Movie(1,"superman","good movie","www.abc.com","2015-03-03",45.5,112,"user1"));
        final Movie movie = repo.getOne(1);
        assertTrue(movie.getId().equals(1));
    }

    @Test
    public void testUpdateMovie() throws Exception{
        repo.save(new Movie(1,"superman","good movie","www.abc.com","2015-03-03",45.5,112,"user1"));
        final Movie movie = repo.getOne(1);
        assertEquals(movie.getName(),"superman");
        movie.setComments("hi");
        repo.save(movie);
        final Movie tempMovie = repo.getOne(1);
        assertEquals(tempMovie.getComments(),"hi");
    }

    @Test
    public void testDeleteMovie() throws Exception{
        repo.save(new Movie(1,"superman","good movie","www.abc.com","2015-03-03",45.5,112,"user1"));
        final Movie movie = repo.getOne(1);
        assertEquals(movie.getName(),"superman");
        repo.delete(movie);
        assertEquals(Optional.empty(),repo.findById(1));
    }

    @Test
    public void testGetMovie() throws Exception{
        repo.save(new Movie(1,"superman","good movie","www.abc.com","2015-03-03",45.5,112,"user1"));
        final Movie movie = repo.getOne(1);
        assertEquals(movie.getName(),"superman");
    }

    @Test
    public void testGetAllMovies() throws Exception{
        repo.save(new Movie(1,"superman","good movie","www.abc.com","2015-03-03",45.5,112,"user1"));
        repo.save(new Movie(2,"superman-1","good movie","www.abc.com","2015-03-03",45.5,112,"user1"));
        repo.save(new Movie(3,"superman-2","good movie","www.abc.com","2015-03-03",45.5,112,"user2"));
        final List<Movie> movies = repo.findByUserId("user1");
        assertEquals(movies.get(0).getName(),"superman");
        assertEquals(movies.get(1).getName(),"superman-1");
    }
}