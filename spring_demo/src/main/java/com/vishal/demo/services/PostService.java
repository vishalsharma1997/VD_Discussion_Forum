package com.vishal.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishal.demo.models.Post;
import com.vishal.demo.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    PostRepository repository;

    @Autowired
    UserService userService;

    public List<Post> getAllPosts(){
        return  repository.findAllByOrderByIdDesc();
    }

    public List<Post> getAllPostsByUser(String userEmailId){
        return  repository.findByUserEmailIdOrderByIdDesc(userEmailId);
    }

    public Post addPost(Post post){
        post.setUserEmailId(userService.getLoggedInUserEmailId());
        post.setTimestamp(new Date(System.currentTimeMillis()));

        System.out.println("POST:" + post);

         return repository.save(post);
    }

    public void deletePostById(String Id){
        repository.deleteById(Id);
    }
}
