package com.stackroute.moviecruiser.services;

import com.stackroute.moviecruiser.exceptions.UserAlreadyExistsException;
import com.stackroute.moviecruiser.exceptions.UserNotFoundException;
import com.stackroute.moviecruiser.model.User;
import com.stackroute.moviecruiser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo){
        super();
        this.userRepo = userRepo;
    }

    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
        Optional<User> ul = userRepo.findById(user.getUserId());
        if (ul.isPresent()){
            throw new UserAlreadyExistsException("User with Id already exists");
        }

        userRepo.save(user);
        return true;
    }


    @Override
    public User findByUserId(String userId) throws UserNotFoundException {
        Optional<User> optional = userRepo.findById(userId);
        if (!optional.isPresent()) {
            throw new UserNotFoundException("UserId not found");
        }
        return optional.get();
    }
}
