package com.soumya.blog_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.soumya.blog_application.payloads.PostDTO;
import com.soumya.blog_application.payloads.PostResponse;
import com.soumya.blog_application.service.PostService;
import java.util.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/category/{categoryId}/user/{userId}")
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO,@PathVariable Integer categoryId,@PathVariable Integer userId){
        return new ResponseEntity<>(this.postService.createPost(postDTO, categoryId, userId),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Integer id){
        return new ResponseEntity<>(this.postService.upadatePost(postDTO, id),HttpStatus.OK);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostDTO>> getAllPostByUser(@PathVariable Integer id){
        List<PostDTO> list=this.postService.getAllByUser(id);
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDTO>> getAllPostByCategory(@PathVariable Integer id){
        List<PostDTO> list=this.postService.getAllByCategory(id);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getAllPostOfUserWithCategories(@PathVariable Integer userId,@PathVariable Integer categoryId){
        List<PostDTO> list=this.postService.getAllPostOfUserWithCategories(userId, categoryId);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer id){
        PostDTO post=this.postService.deletePost(id);
        return new ResponseEntity<>(post,HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Integer id){
        PostDTO postDTO=this.postService.deletePost(id);
        return new ResponseEntity<>(postDTO,HttpStatus.GONE);
    }

    @GetMapping("/all")
    public ResponseEntity<PostResponse> getAllPost(
        @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
        @RequestParam(value = "pageSize",defaultValue = "3",required = false) Integer pageSize
    ){
        PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<>(postResponse,HttpStatus.FOUND);
    }

}
