package com.stackroute.moviecruiser.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiser.model.User;
import com.stackroute.moviecruiser.services.SecurityTokenGenerator;
import com.stackroute.moviecruiser.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private SecurityTokenGenerator securityTokenGenerator;

    private User user;

    @InjectMocks
    UserController userController;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        user = new User("Jhon123","Jhon","Peter","123456",new Date());
    }

    @Test
    public void testRegisterUser() throws Exception{
        when(userService.saveUser(user)).thenReturn(true);
        mockMvc.perform(post("/api/v1/userservice/register").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isCreated()).andDo(print());
        verify(userService,times(1)).saveUser(Mockito.any(User.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testLoginUser() throws Exception{
        String userId = "Jhon123";
        String password = "123456";
        when(userService.saveUser(user)).thenReturn(true);
        when(userService.findByUserId(userId)).thenReturn(user);
        mockMvc.perform(post("/api/v1/userservice/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(status().isOk());
        verify(userService,times(1)).findByUserId(user.getUserId());
        verifyNoMoreInteractions(userService);
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