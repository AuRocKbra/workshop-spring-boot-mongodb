package com.aurock.workshop.mongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.aurock.workshop.mongo.domain.User;
import com.aurock.workshop.mongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gamil.com");
		User alex = new User(null, "Alex Green", "alex@gamil.com");
		User bob = new User(null, "Bob Grey", "bob@gamil.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
	}

}
