package com.workspace;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }

    @RequestMapping("/api/users")
    public List<Users> getAll(){
        return  usersRepository.findAll();
    }
 }
