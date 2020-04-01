package com.note.back.controller;

import com.note.back.Response.Response;
import com.note.back.pojo.User;
import com.note.back.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.persistence.Id;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("api/register")
    @ResponseBody
    public String register(@RequestBody User user){
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        String password = user.getPassword();

        boolean isExist = userService.isExist(username);
        if(isExist){
            return "用户名已经存在";
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithm = "md5";
        String pwdAfterHash = new SimpleHash(algorithm,password,salt,times).toString();
        user.setSalt(salt);
        user.setPassword(pwdAfterHash);
        userService.addUser(user);
        return "成功";
    }

    @CrossOrigin
    @PostMapping("api/login")
    @ResponseBody
    public Response login(@RequestBody User user){
        String username = user.getUsername();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,user.getPassword());

        try{
            subject.login(usernamePasswordToken);
            return new Response(200,"success",usernamePasswordToken);
        }catch (AuthenticationException e){
            return new Response(500,"failure",null);
        }
    }

    @CrossOrigin
    @GetMapping("api/logout")
    @ResponseBody
    public Response logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Response(200,"登出",null);
    }
}
