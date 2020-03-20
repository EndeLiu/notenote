package com.note.back.controller;

import com.note.back.pojo.User;
import com.note.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Id;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("api/register")
    @ResponseBody
    public Integer Register(){
        User user = userService.getUserByUsername("leo");
        return user.getId();
    }
}
