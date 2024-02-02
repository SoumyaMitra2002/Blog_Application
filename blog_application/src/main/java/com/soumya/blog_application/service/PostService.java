package com.soumya.blog_application.service;

import java.util.List;

import com.soumya.blog_application.payloads.PostDTO;
import com.soumya.blog_application.payloads.PostResponse;

public interface PostService {
    public PostDTO createPost(PostDTO postDTO,Integer categoryId,Integer userId);
    public PostDTO upadatePost(PostDTO postDTO,Integer postId);
    public PostDTO deletePost(Integer postId);
    public PostDTO getPostById(Integer postId);
    public List<PostDTO> getAllByUser(Integer userId);
    public List<PostDTO> getAllByCategory(Integer categoryId);
    public List<PostDTO> getAllPostOfUserWithCategories(Integer userId,Integer categoryId);
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
    public List<PostDTO> getPostByTitle(String keyword);

}
