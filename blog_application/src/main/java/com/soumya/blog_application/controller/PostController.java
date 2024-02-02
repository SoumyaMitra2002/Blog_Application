package com.soumya.blog_application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.soumya.blog_application.payloads.ApiResponse;
import com.soumya.blog_application.payloads.PostDTO;
import com.soumya.blog_application.payloads.PostResponse;
import com.soumya.blog_application.service.FileService;
import com.soumya.blog_application.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostService postService;
    
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
        @RequestParam(value = "pageSize",defaultValue = "3",required = false) Integer pageSize,
        @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
        @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.FOUND);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDTO>> getAllPostByTitle(@PathVariable String keyword){
        List<PostDTO> list=this.postService.getPostByTitle(keyword);
        return new ResponseEntity<>(list,HttpStatus.OK);

    }

    @PostMapping("/image/upload/{id}")
    public ResponseEntity<ApiResponse> uploadPostImage(@PathVariable Integer id,
    @RequestParam("image") MultipartFile file) throws IOException{
        String fileName=this.fileService.uploadImage(path, file);
        PostDTO postDTO=this.postService.getPostById(id);
        postDTO.setImageName(fileName);
        this.postService.upadatePost(postDTO, id);
        ApiResponse response=new ApiResponse();
        response.setMessage("Image Uploaded Successfully");
        response.setStatus(true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/image/{postId}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable Integer postId,HttpServletResponse response) throws IOException{
        PostDTO postDTO=this.postService.getPostById(postId);
        InputStream resource=this.fileService.getresourse(path, postDTO.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
    
}
