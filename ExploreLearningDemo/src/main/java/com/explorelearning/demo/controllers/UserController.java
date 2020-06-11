package com.explorelearning.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.explorelearning.demo.user.User;
import com.explorelearning.demo.user.UserReposirory;

@RestController
public class UserController {	
	
	@Autowired
	private UserReposirory userRepo;
	
	//Getting All the users
	@GetMapping(path="/users")
	public List<User> getAllUsers(){
		return userRepo.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
	}

	//Getting a specific user
	@GetMapping(path="/users/{id}")
	public Optional<User> getSpecificUser(@PathVariable int id){
		return userRepo.findById(id);
	}
	
	//Adding a new user
	@PostMapping(path="/users")
	public String addUser(@Valid @RequestBody User user){
		List<User> getList=userRepo.findAll();
		for(User name: getList) {
			if(name.getFirstName().equalsIgnoreCase(user.getFirstName())
					&& name.getLastName().equalsIgnoreCase(user.getLastName())){
				return "First Name and Last Name already exist";
			}
			else {
				userRepo.save(user);
			}
		}
		return "Created";
		  
		
	}

	//Deleating a aspecific user
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id){
		  userRepo.deleteById(id);
		
	}

	
}
