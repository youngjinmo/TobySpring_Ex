package com.bomstart.tobyspring.user.controller;

import com.bomstart.tobyspring.user.domain.User;
import com.bomstart.tobyspring.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/create")
    public @ResponseBody void addUser(User user){
        userService.add(user);
    }

    @PostMapping("/delete/{id}")
    public @ResponseBody void removeUser(@PathVariable  String id){
        userService.remove(id);
    }
}
