package com.stackroute.moviecruiser.repositories;

import com.stackroute.moviecruiser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
