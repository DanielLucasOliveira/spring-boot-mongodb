package com.danieldev.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danieldev.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		User teste = new User("1", "Daniel", "Daniel@gmail.com");
		User teste2 = new User("1", "Daniel", "Daniel@gmail.com");
		List<User> list = new ArrayList<User>();
		list.addAll(Arrays.asList(teste, teste2));
		return ResponseEntity.ok().body(list);
	}
}
