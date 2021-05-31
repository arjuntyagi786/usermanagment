package com.userdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.userdemo.repository.UserRepository;
import com.userdemo.model.User;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

	@PostMapping("/users/register")
	public Status registerUser(@Valid @RequestBody User newUser) {
		List<User> users = userRepository.findAll();
		System.out.println("New user: " + newUser.toString());
		for (User user : users) {
			System.out.println("Registered user: " + newUser.toString());
			if (user.equals(newUser)) {
				System.out.println("User Already exists!");
				return Status.USER_ALREADY_EXISTS;
			}
		}
		userRepository.save(newUser);
		return Status.REGISTERED_SUCCESSFULLY;
	}

	@PostMapping("/users/login")
	public LoginResponse loginUser(@Valid @RequestBody User paramUser) {
		User user = userRepository.findByUsername(paramUser.getUsername());
		LoginResponse loginresult = new LoginResponse();
		if (user == null) {
			loginresult.setResponsestatus(Status.LOGIN_FAILED);

			return loginresult;
		} else {
			if (user.getPassword().equals(paramUser.getPassword())) {
				System.out.println("CHECK your id" + user.getId());
				System.out.println(user.getId());
				UUID uuid = UUID.randomUUID();
				String uuidAsString = uuid.toString();

				user.setApi_token(uuidAsString);
				userRepository.save(user);
				loginresult.setResponsestatus(Status.LOGGEDIN_SUCCESSFULLY);
				loginresult.setToken(uuidAsString);
				return loginresult;
			} else {
				loginresult.setResponsestatus(Status.LOGIN_FAILED);
				return loginresult;

			}

		}
	}

	@PostMapping("/users/Home")
	public User homeUser(@Valid @RequestBody User paramUser) {
		User user = userRepository.findByApitoken(paramUser.isApi_token());

		if (user == null) {

			return user;
		} else {
			return user;

		}

	}

}