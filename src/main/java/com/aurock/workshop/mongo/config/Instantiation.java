package com.aurock.workshop.mongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.aurock.workshop.mongo.domain.Post;
import com.aurock.workshop.mongo.domain.User;
import com.aurock.workshop.mongo.dto.AutorDTO;
import com.aurock.workshop.mongo.dto.ComentarioDTO;
import com.aurock.workshop.mongo.repository.PostRepository;
import com.aurock.workshop.mongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
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
		
		User maria = new User(null, "Maria Brown", "maria@gamil.com");
		User alex = new User(null, "Alex Green", "alex@gamil.com");
		User bob = new User(null, "Bob Grey", "bob@gamil.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu viagem","Vou viajar para São Paulo. Abraços!",new AutorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"),"Bom dia","Acordei feliz hoje!",new AutorDTO(maria));
		
		ComentarioDTO c1 = new ComentarioDTO("Boa viagem mano!",sdf.parse("21/03/2018"),new AutorDTO(alex));
		ComentarioDTO c2 = new ComentarioDTO("Aproveite",sdf.parse("22/03/2018"),new AutorDTO(bob));
		ComentarioDTO c3 = new ComentarioDTO("Tenha um ótimo dia",sdf.parse("23/03/2018"),new AutorDTO(alex));
		
		post1.getComentarios().addAll(Arrays.asList(c1,c2));
		post2.getComentarios().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
	}

}
