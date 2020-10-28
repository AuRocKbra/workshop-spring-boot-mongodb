package com.aurock.workshop.mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aurock.workshop.mongo.domain.Post;
import com.aurock.workshop.mongo.domain.User;
import com.aurock.workshop.mongo.dto.UserDTO;
import com.aurock.workshop.mongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResources {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> users = userService.findAll();
		List<UserDTO>usersDTO = users.stream().map(x->new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User objUser= userService.findById(id);
		UserDTO userDTO = new UserDTO(objUser);
		return ResponseEntity.ok().body(userDTO);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
		User objUser= userService.fromDTO(objDTO);
		objUser = userService.insert(objUser);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id){
		User objUser= userService.fromDTO(objDTO);
		objUser.setId(id);
		objUser = userService.update(objUser);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User objUser= userService.findById(id);
		return ResponseEntity.ok().body(objUser.getPosts());
	}
}
