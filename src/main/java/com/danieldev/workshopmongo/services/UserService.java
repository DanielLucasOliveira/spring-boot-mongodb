package com.danieldev.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieldev.workshopmongo.domain.User;
import com.danieldev.workshopmongo.repository.UserRepository;
import com.danieldev.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}

	public Optional<User> findByUserId(String userId){
		Optional<User> user = repository.findById(userId);
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;

	}
}
