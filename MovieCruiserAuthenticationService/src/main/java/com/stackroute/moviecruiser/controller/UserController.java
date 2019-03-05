package com.stackroute.moviecruiser.controller;

import com.stackroute.moviecruiser.exceptions.UserAlreadyExistsException;
import com.stackroute.moviecruiser.exceptions.UserNotFoundException;
import com.stackroute.moviecruiser.model.User;
import com.stackroute.moviecruiser.services.SecurityTokenGenerator;
import com.stackroute.moviecruiser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Map;

@RestController
@EnableWebMvc
@RequestMapping("/api/v1/userservice")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityTokenGenerator tokenGenerator;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Validated @RequestBody User user){
        try {
            userService.saveUser(user);
            return new ResponseEntity<String>("User registered successfully",HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            return new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}", HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@Validated @RequestBody User loginDetail){
        try {
            String userId = loginDetail.getUserId();
            String password = loginDetail.getPassword();

            User user = userService.findByUserId(userId);
            String pwd = user.getPassword();
            if (!password.equals(pwd)){
                return new ResponseEntity("{\"message\":\"\"Invalid login credential, Please check username and password\"}",HttpStatus.UNAUTHORIZED);
            }

            Map<String,String> map = tokenGenerator.generateToken(user);
            return new ResponseEntity(map,HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
