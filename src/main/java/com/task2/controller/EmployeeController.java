package com.task2.controller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class EmployeeController {
//    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    @GetMapping("/greeting")
    public List<GrantedAuthority> getEmployees() {

        //get authorities
        return ((List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities());


    }
}