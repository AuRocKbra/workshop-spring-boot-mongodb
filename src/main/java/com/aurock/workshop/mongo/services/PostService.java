package com.aurock.workshop.mongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurock.workshop.mongo.domain.Post;
import com.aurock.workshop.mongo.repository.PostRepository;
import com.aurock.workshop.mongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		try {
			Optional<Post> user = postRepository.findById(id);
			return user.get();
		}catch(RuntimeException e ) {
			throw new ObjectNotFoundException("Recurso não encontrado");
		}
	}
	
	public List<Post> findByTitle(String txt){
		return postRepository.searchTitle(txt);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime()+24*60*60*1000);
		return postRepository.fullSearch(text, minDate, maxDate); 
	}
}
