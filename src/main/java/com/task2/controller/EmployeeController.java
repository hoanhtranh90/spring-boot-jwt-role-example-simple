package com.task2.controller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin()
public class EmployeeController {
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public List<GrantedAuthority> getEmployees() {
        return ((List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities());


    }
}