package com.danieldev.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.danieldev.workshopmongo.domain.Post;
import com.danieldev.workshopmongo.domain.User;
import com.danieldev.workshopmongo.dto.AutorDTO;
import com.danieldev.workshopmongo.dto.ComentDTO;
import com.danieldev.workshopmongo.repository.PostRepository;
import com.danieldev.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();
        
        User maria = new User(null, "maria", "maria@gmail.com");
        User daniel = new User(null, "Daniel Lucas", "dandan@gmail.com");
        User john = new User(null, "john", "john@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, daniel, john));
        
        Post post1 = new Post(null, sdf.parse("21/03/2024"), "Partiu viagem", "Vou viajar para São Paulo, abraços!", new AutorDTO(maria));
        Post post2 = new Post(null, sdf.parse("12/03/2024"), "Partiu trabalho", "Vou começar no meu novo emprego, muito feliz!!", new AutorDTO(daniel));
        
        ComentDTO c1 = new ComentDTO("Boa viagem!", sdf.parse("21/03/2024"), new AutorDTO(daniel));
        ComentDTO c2 = new ComentDTO("Boa viagem!", sdf.parse("21/03/2024"), new AutorDTO(john));
        ComentDTO c3 = new ComentDTO("Que notícia boa!", sdf.parse("21/03/2024"), new AutorDTO(maria));
        
        post1.getComents().addAll(Arrays.asList(c1, c2));
        post2.getComents().addAll(Arrays.asList(c3));
        
        postRepository.saveAll(Arrays.asList(post1, post2));
        
        maria.getPosts().add(post1);
        daniel.getPosts().add(post2);
        userRepository.saveAll(Arrays.asList(maria, daniel));

    }
    
}
