package com.danieldev.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieldev.workshopmongo.domain.Post;
import com.danieldev.workshopmongo.repository.PostRepository;
import com.danieldev.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Optional<Post> findById(String id) {
		Optional<Post> obj = repo.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return obj;
	}

}
