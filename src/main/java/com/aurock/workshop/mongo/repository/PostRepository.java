package com.aurock.workshop.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aurock.workshop.mongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {

}
