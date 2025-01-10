package com.workspace;


import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UsersRepository usersRepository;

  /*  @Bean
    ApplicationRunner applicationRunner(){
        return  a-> checkAssert(null);
    }


    private void checkAssert(Object object){
        Assert.notNull(object,"Object will never null");
    }*/



    public UserController(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }

    @RequestMapping("/api/users")
    public List<Users> getAll(){
        return  usersRepository.findAll();
    }
 }
