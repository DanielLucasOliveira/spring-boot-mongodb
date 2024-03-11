package com.danieldev.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.danieldev.workshopmongo.domain.Post;
import com.danieldev.workshopmongo.domain.User;
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

        Post post1 = new Post(null, sdf.parse("21/03/2024"), "Partiu viagem", "Vou viajar para São Paulo, abraços!", maria);
        Post post2 = new Post(null, sdf.parse("12/03/2024"), "Partiu trabalho", "Vou começar no meu novo emprego, muito feliz!!", daniel);
        
        userRepository.saveAll(Arrays.asList(maria, daniel, john));
        postRepository.saveAll(Arrays.asList(post1, post2));

    }
    
}
