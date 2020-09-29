package com.task2.controller;

import com.task2.authencation.JwtTokenUtil;
import com.task2.model.JwtRequest;
import com.task2.model.JwtResponse;
import com.task2.model.User;
import com.task2.model.UserDto;
import com.task2.authencation.JwtUserDetailsService;
import com.task2.repository.UserRepository;
import com.task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			throw new Exception("ABCDEF");
		}
	}

	@PutMapping("/authenticate")
	public ResponseEntity<?> UpdateAcc(@RequestParam(value = "id", required = true) Long user_id,
									   @RequestBody UserDto userDto) {

		userService.updateAccount(user_id, userDto);

		return new ResponseEntity("Update thanh cong", HttpStatus.OK);
	}

	@DeleteMapping("/authenticate")
	public ResponseEntity<?> DeleteAcc(@RequestParam(value = "id", required = true) Long user_id) {

		userService.deleteAccount(user_id);


		return new ResponseEntity("Delete thanh cong", HttpStatus.OK);
	}
}
