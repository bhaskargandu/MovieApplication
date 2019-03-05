package com.stackroute.moviecruiser.repositories;

import com.stackroute.moviecruiser.model.User;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;

    private User user;

    @Before
    public void setUp() throws Exception{
        user = new User("Joh","John jenny","Bob","123456", new Date());
    }

    @Test
    public void testRegisterUserSuccess(){
        userRepo.save(user);
        User object = userRepo.findById(user.getUserId()).get();
        assertTrue(object.getUserId().equals(user.getUserId()));
    }
}