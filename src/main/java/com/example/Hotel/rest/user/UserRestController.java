package com.example.Hotel.rest.user;

import com.example.Hotel.domain.auth.UserEntity;
import com.example.Hotel.domain.auth.UserMapper;
import com.example.Hotel.domain.auth.UserService;
import com.example.Hotel.web.user.UserForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/restList")
    public List<UserEntity> userList(UserForm form) {
        System.out.println("testrest__" + form.getName());

        List<UserEntity> users = userService.findUsers(form.getName());
        return users;
    }

}
