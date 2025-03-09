package com.example.blog.repositories;

import com.example.blog.entities.Post;
import com.example.blog.entities.Category;
import com.example.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);

    List<Post> findByTitleContaining(String title);

//    @Query("select p from Post p where p.title like :key")
//    List<Post> searchByTitle(@Param("key") String title);

}
