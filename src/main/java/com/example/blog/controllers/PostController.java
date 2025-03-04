package com.example.blog.controllers;


import com.example.blog.entities.Post;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.PostDTO;
import com.example.blog.services.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDTO postCreated = this.postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(postCreated, HttpStatus.CREATED);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId) {
        PostDTO updatePost = postService.updatePost(postDTO, postId);
        return ResponseEntity.ok(updatePost);
    }

    // get posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDTO> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // get posts by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDTO> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize
    ) {
        List<PostDTO> allPosts = this.postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    // get post by postId
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        PostDTO post = this.postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    // delete post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>( new ApiResponse("Post deleted Successfully", true ), HttpStatus.OK);
    }

}
