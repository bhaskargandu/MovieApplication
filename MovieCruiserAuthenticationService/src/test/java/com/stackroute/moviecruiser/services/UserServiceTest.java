package com.stackroute.moviecruiser.services;

import com.stackroute.moviecruiser.exceptions.UserAlreadyExistsException;
import com.stackroute.moviecruiser.exceptions.UserNotFoundException;
import com.stackroute.moviecruiser.model.User;
import com.stackroute.moviecruiser.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private User user;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    Optional<User> options;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        user = new User("sonu3706","Chandan","Mishra","123456",new Date());
        options = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws UserNotFoundException, UserAlreadyExistsException{

        when(userRepository.save(user)).thenReturn(user);
        boolean flag = userServiceImpl.saveUser(user);
        assertEquals("Cannot Register User",true,flag);
        verify(userRepository,times(1)).save(user);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testSaveUserFailure() throws UserNotFoundException,UserAlreadyExistsException{
        when(userRepository.findById(user.getUserId())).thenReturn(options);
        when(userRepository.save(user)).thenReturn(user);
        boolean flag = userServiceImpl.saveUser(user);
    }

    @Test
    public void testValidateSuccess() throws UserNotFoundException{
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        User userResult = userServiceImpl.findByUserId(user.getUserId());
        assertNotNull(userResult);
        assertEquals("sonu3706",userResult.getUserId());
        verify(userRepository, times(1)).findById(user.getUserId());
    }


    @Test(expected = UserNotFoundException.class)
    public void testValidateFailure() throws UserNotFoundException{
        when(userRepository.findById("sonu")).thenReturn(null);
        userServiceImpl.findByUserId(user.getUserId());
    }

}