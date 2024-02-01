package com.soumya.blog_application.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.soumya.blog_application.exception.ResourseNotFoundException;
import com.soumya.blog_application.entity.Category;
import com.soumya.blog_application.entity.Post;
import com.soumya.blog_application.entity.User;
import com.soumya.blog_application.payloads.CategoryDTO;
import com.soumya.blog_application.payloads.PostDTO;
import com.soumya.blog_application.payloads.PostResponse;
import com.soumya.blog_application.payloads.UserDTO;
import com.soumya.blog_application.repository.CategoryRepo;
import com.soumya.blog_application.repository.PostRepo;
import com.soumya.blog_application.repository.UserRepo;
import com.soumya.blog_application.service.PostService;

@Service
public class PostImpl implements PostService{

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourseNotFoundException("User", "User Id", userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category", "Category Id", categoryId));
        Post post=this.modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        
        Post saved=this.postRepo.save(post);
        PostDTO result=this.modelMapper.map(saved, PostDTO.class);
        result.setCategoryDTO(this.modelMapper.map(category, CategoryDTO.class));
        result.setUserDTO(this.modelMapper.map(user, UserDTO.class));
        return result;
    }

    @Override
    public PostDTO upadatePost(PostDTO postDTO,Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourseNotFoundException("Post", "Post Id", postId));
        if(postDTO.getTitle()!=null){
            post.setTitle(postDTO.getTitle());
        }
        if(postDTO.getContent()!=null){
            post.setContent(postDTO.getContent());
        }
        if(postDTO.getImageName()!=null){
            post.setImageName(postDTO.getImageName());
        }
        Post saved=this.postRepo.save(post);

        return this.modelMapper.map(saved, PostDTO.class);
    }

    @Override
    public PostDTO deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourseNotFoundException("Post", "Post Id", postId));
        this.postRepo.delete(post);
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourseNotFoundException("Post", "Post Id", postId));
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourseNotFoundException("User", "User Id", userId));
        List<Post> posts=this.postRepo.findByUser(user);
        List<PostDTO> postDTOs=new ArrayList<>();
        posts.forEach(p-> postDTOs.add(this.modelMapper.map(p, PostDTO.class)));
        return postDTOs;
    }

    @Override
    public List<PostDTO> getAllByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("Category","Category Id", categoryId));
        List<PostDTO> postDTOs=new ArrayList<>();
        this.postRepo.findByCategory(category).forEach(p-> postDTOs.add(this.modelMapper.map(p, PostDTO.class)));
        return postDTOs;

    }

    @Override
    public List<PostDTO> getAllPostOfUserWithCategories(Integer userId, Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourseNotFoundException("User", "User Id", userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("Category","Category Id", categoryId));
        return this.postRepo.findByUserAndCategory(user, category)
        .stream()
        .map(p->this.modelMapper.map(p, PostDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize) {
        Pageable p=PageRequest.of(pageNumber, pageSize);
        Page<Post> page=this.postRepo.findAll(p);
        List<Post> list=page.getContent();
        PostResponse postResponse=new PostResponse();
        List<PostDTO> list2=list.stream().map(post-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        postResponse.setContent(list2);  
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setTotalRecords(page.getTotalElements());
        postResponse.setLastPage(page.isLast());

        return postResponse;
        
    }
    
}
