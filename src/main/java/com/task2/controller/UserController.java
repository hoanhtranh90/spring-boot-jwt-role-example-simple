package com.task2.controller;

import com.task2.model.User;
import com.task2.repository.UserRepository;
import com.task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public Optional<User> osp_user(@PathVariable("id") Long user_id) {
        return userRepository.findById(user_id);
    }
    //demo: http://localhost:3000/user/66



    @GetMapping()
    Page<User> employeesPageable(Pageable pageable) {
        return userService.showDataPage(pageable);
    }
    //demo: http://localhost:3000/user?page=1&size=5&sort=first_name


    @GetMapping("/seach")
    Page<User> SeachPage(
            @RequestParam(value = "name", required = false) String name,
            Pageable pageable
    ) {
        return userService.showDataSeach(name,pageable);
    }
    //demo: http://localhost:3000/user/seach?name=a&page=1&size=5&sort=first_name


}
