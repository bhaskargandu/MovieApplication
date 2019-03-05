package com.stackroute.moviecruiser.services;

import com.stackroute.moviecruiser.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String,String> generateToken(User user);
}
