package com.task2.authencation;

import com.task2.model.Role;
import com.task2.model.User;
import com.task2.model.UserDto;
import com.task2.repository.RoleRepository;
import com.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role role : user.getRoles()){
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true,true,true,true,authorities);
	}
//save user
	public User save(UserDto user) {
		User u = userRepository.findByUsername(user.getUsername());

		if (u != null) {
			//loi tai khoan da ton tai(viet tam)
			throw new UsernameNotFoundException("abc");
		} else {
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			//save role default


			//default = user
			newUser.setRoles(roleRepository.findAllById(Collections.singleton(2)));

			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			return userRepository.save(newUser);
		}
	}
}