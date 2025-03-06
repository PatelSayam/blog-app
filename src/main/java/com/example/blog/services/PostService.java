package com.example.blog.services;

import com.example.blog.payloads.PostDTO;
import com.example.blog.payloads.PostResponse;

import java.util.List;


public interface PostService {

    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    PostDTO updatePost(PostDTO postDTO, Integer postId);

    void deletePost(Integer postId);

    // get single post by id
    PostDTO getPostById(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    //get all post by category
    List<PostDTO> getPostsByCategory(Integer categoryId);

    // get all post by user
    List<PostDTO> getPostsByUser(Integer userId);

    // search posts by keyword
    List<PostDTO> searchPosts(String keyword);

}
