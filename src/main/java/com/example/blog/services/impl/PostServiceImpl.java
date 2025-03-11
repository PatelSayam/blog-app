package com.example.blog.services.impl;

import com.example.blog.entities.User;
import com.example.blog.entities.Category;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.PostDTO;
import com.example.blog.payloads.PostResponse;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.repositories.PostRepo;
import com.example.blog.repositories.UserRepo;
import com.example.blog.entities.Post;
import com.example.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        Post post = modelMapper.map(postDTO, Post.class);

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post createdPost = postRepo.save(post);
        return modelMapper.map(createdPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        post.setImageName(postDTO.getImageName());

        Post updatePost = this.postRepo.save(post);

        return modelMapper.map(updatePost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

        this.postRepo.delete(post);
    }

    @Override
    public PostDTO getPostById(Integer postId) {

       Post post =  this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

       return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = this.postRepo.findAll(p);

        List<Post> allPosts = pagePost.getContent();

        List<PostDTO> postDTOs = allPosts.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).toList();

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDTOs);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());

        postResponse.setTotalPage(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        List<Post> posts = this.postRepo.findByCategory(category);

        return posts.stream()
                .map(post -> this.modelMapper.map(post, PostDTO.class))
                .toList();
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        return posts.stream()
                .map(post -> this.modelMapper.map(post, PostDTO.class))
                .toList();
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {

        List<Post> posts = postRepo.findByTitleContaining(keyword);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).toList();

    }
}
