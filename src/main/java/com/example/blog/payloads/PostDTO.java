package com.example.blog.payloads;


import com.example.blog.payloads.CategoryDTO;
import com.example.blog.payloads.UserDTO;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDTO category;

    private UserDTO user;
}
