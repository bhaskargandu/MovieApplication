package com.stackroute.moviecruiser.services;

import com.stackroute.moviecruiser.exceptions.UserAlreadyExistsException;
import com.stackroute.moviecruiser.exceptions.UserNotFoundException;
import com.stackroute.moviecruiser.model.User;

public interface UserService {

    boolean saveUser(User user) throws UserAlreadyExistsException;

    User findByUserId(String userId) throws UserNotFoundException;
}
