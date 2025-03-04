package com.example.blog.repositories;

import com.example.blog.entities.Post;
import com.example.blog.entities.Category;
import com.example.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
}
