package com.task2.service;

import com.task2.model.Role;
import com.task2.model.User;
import com.task2.repository.RoleRepository;
import com.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Page<User> showDataSeach(String name, Pageable pageable) {
        return userRepository.findByFirstnameEndsWith(name,pageable);

    }


    public Page<User> showDataPage(Pageable pageable) {
        return userRepository.showAllUser(pageable);
    }


    public Page<User> findUserByRole(String role, Pageable pageable) {
        return roleRepository.findByRole(role,pageable);
    }
}
