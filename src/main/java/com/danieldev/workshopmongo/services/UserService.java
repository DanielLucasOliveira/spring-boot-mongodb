package com.danieldev.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieldev.workshopmongo.domain.User;
import com.danieldev.workshopmongo.dto.UserDTO;
import com.danieldev.workshopmongo.repository.UserRepository;
import com.danieldev.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public Optional<User> findByUserId(String userId) {
		Optional<User> user = repository.findById(userId);
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}

	public User insert(User obj) {
		return repository.insert(obj);
	}

	public void delete(String id) {
		findByUserId(id);
		repository.deleteById(id);
	}

	public User update(User obj) {
		Optional<User> newUser = repository.findById(obj.getId());
		if (newUser.isPresent()) {
			User newObj = newUser.get();
			updateData(newObj, obj);
			return repository.save(newObj);
		} else {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
	}

	private void updateData(User newUser, User obj) {
		newUser.setName(obj.getName());
		newUser.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
