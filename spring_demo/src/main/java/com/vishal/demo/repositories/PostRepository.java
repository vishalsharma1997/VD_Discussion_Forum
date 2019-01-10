package com.vishal.demo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vishal.demo.models.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	public List<Post> findByUserEmailIdOrderByIdDesc(String userEmailId);
	public List<Post> findAllByOrderByIdDesc();
}
