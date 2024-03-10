package com.danieldev.workshopmongo.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danieldev.workshopmongo.domain.User;
import com.danieldev.workshopmongo.dto.UserDTO;
import com.danieldev.workshopmongo.services.UserService;
import com.danieldev.workshopmongo.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		Optional<User> obj = service.findByUserId(id);
		if(obj.isPresent()) {
			User user = obj.get();
			return ResponseEntity.ok().body(new UserDTO(user));
		} else {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
				
	}
}
