package com.aurock.workshop.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurock.workshop.mongo.domain.User;
import com.aurock.workshop.mongo.dto.UserDTO;
import com.aurock.workshop.mongo.repository.UserRepository;
import com.aurock.workshop.mongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		try {
			Optional<User> user = userRepository.findById(id);
			return user.get();
		}
		catch(RuntimeException e){
			throw new ObjectNotFoundException("Recurso não encontrado");
		}
	}
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());	
	}
	
	public void deleteById(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = userRepository.findById(obj.getId()).get();
		updateData(newObj,obj);
		return userRepository.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setId(obj.getId());
		newObj.setName(obj.getName());
	}
}
