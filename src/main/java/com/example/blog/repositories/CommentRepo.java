package com.example.blog.repositories;
import com.example.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepo  extends JpaRepository<Comment, Integer> {

}