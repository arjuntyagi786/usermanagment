package com.userdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.userdemo.modal.User;
import com.userdemo.repository.Status;
import com.userdemo.repository.UserRepository;
import com.userdemo.repository.LoginResult;

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
        return Status.SUCCESS;
    }
    @PostMapping("/users/login")
    public LoginResult loginUser(@Valid @RequestBody User user) {
        User users = userRepository.findByUsername(user.getUsername());
        LoginResult loginresult = new LoginResult();
        if(users==null) {
        	loginresult.setResponsestatus(Status.FAILURE);
        	
              return loginresult;
        }
        else {
        	if(users.getPassword().equals(user.getPassword())) {
        		  System.out.println("CHECK your id"+users.getId());
                  System.out.println(users.getId());
                  UUID uuid = UUID. randomUUID();
                  String uuidAsString = uuid. toString();

                  users.setApi_token(uuidAsString);
                userRepository.save(users);
                loginresult.setResponsestatus(Status.SUCCESS);
            	loginresult.setToken(uuidAsString);
            	 return loginresult;	
        	}
        	else{
        		loginresult.setResponsestatus(Status.FAILURE);
        		return loginresult;
        		
            	
        		
        	}
        	
        }
    }
        @PostMapping("/users/Home")
        public Status homeUser(@Valid @RequestBody User user) {
            User users = userRepository.findByApitoken(user.isApi_token());
          
            if(users==null) {
            	
                  return Status.FAILURE;
            }
            else {
            	return Status.SUCCESS;
//            	if(users.getPassword().equals(user.getPassword())) {
//            		  System.out.println("CHECK your id"+users.getId());
//                      System.out.println(users.getId());
//                      UUID uuid = UUID. randomUUID();
//                      String uuidAsString = uuid. toString();
//
//                      users.setApi_token(uuidAsString);
//                    userRepository.save(users);
//                	 return Status.SUCCESS;	
//            	}
//            	else{
//            		return Status.FAILURE;
            		
            	}
            	
            }
        
       // System.out.println("login"+users.get(0).getId());
//        User val=users.get(0);
//        val.setLoggedIn(true);
//        User obj=userRepository.findById(users.get(0).getId()).get();
//        obj.setPassword("4234");
//        userRepository.save(obj);
//        other.setLoggedIn(true);
//        for (User other : users) {
//            if (other.equals(user)) {
//            	other.setLoggedIn(true);
//                userRepository.save(other);
//                //userRepository.setUserInfoByUserName(user);
//                return Status.SUCCESS;
//            }
//        }
       

    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
             //   user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }
}